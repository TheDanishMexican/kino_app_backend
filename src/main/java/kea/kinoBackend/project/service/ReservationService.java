package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Reservation;
import kea.kinoBackend.project.repository.ReservationRepository;
import kea.kinoBackend.project.repository.SeatRepository;
import kea.kinoBackend.project.repository.ShowingRepository;
import kea.kinoBackend.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private SeatService seatService;
    private ShowingRepository showingRepository;
    private UserWithRolesRepository userRepository;
    private SeatRepository seatRepository;
    private UserWithRolesRepository userWithRolesRepository;

    public ReservationService(ReservationRepository reservationRepository, SeatService seatService,
                              ShowingRepository showingRepository, SeatRepository seatRepository, UserWithRolesRepository userWithRolesRepository) {
        this.reservationRepository = reservationRepository;
        this.seatService = seatService;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.userWithRolesRepository = userWithRolesRepository;
    }

    /**
     * Get all reservations
     * @return a list of all reservations
     */
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationResponses = reservations.stream().map(this::toDTO).toList();

        return reservationResponses;
    }

    /**
     * Get reservation by id
     * @param id - the id of the reservation
     * @return the reservation with the given id
     */
    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return toDTO(reservation);
    }

    /**
     * Add a new reservation
     * @param request - the reservation to add
     * @return the added reservation
     */
    public ReservationDTO addReservation(ReservationDTO request) {
        System.out.println("this is the cinemaID " + request.cinemaId());

        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new reservation");
        }
        Reservation newReservation = new Reservation();
        updateReservation(newReservation, request);
        reservationRepository.save(newReservation);
        return toDTO(newReservation);
    }

    /**
     * Edit a reservation
     * @param original - the id of the original reservation to edit
     * @param request - the new reservation data
     * @return the edited reservation
     */
    public void updateReservation(Reservation original, ReservationDTO request) {

        original.setSeats(request.seats().stream()
                .map(seat -> seatRepository.findById(seat.id()).orElseThrow(() ->
                        new IllegalArgumentException("Seat not found")))
                .collect(Collectors.toList()));
        original.setShowing(showingRepository.findById(request.showingId()).orElseThrow(() ->
                new IllegalArgumentException("Showing not found")));
        original.setCinemaId(showingRepository.findById(request.showingId()).orElseThrow(() ->
                new IllegalArgumentException("Showing not found")).getHall().getCinema().getId());
        original.setHallId(request.hallId());
        original.setTotalPrice(request.totalPrice());
        original.setSeatPrice(request.seatPrice());
        original.setUser(userWithRolesRepository.findById(request.username()).orElseThrow(() ->
                new IllegalArgumentException("User not found")));
    }

    /**
     * Delete a reservation
     * @param id - the id of the reservation to delete
     * @return a response entity with status NO_CONTENT
     */
    public ResponseEntity deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationRepository.delete(reservation);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ReservationDTO toDTO(Reservation reservation) {
        List<SeatDTO> seatDTOs = reservation.getSeats().stream().map(seatService::toDTO).toList();

        return new ReservationDTO(
                reservation.getId(),
                seatDTOs,
                reservation.getShowing().getId(),
                reservation.getHallId(),
                reservation.getTotalPrice(),
                reservation.getSeatPrice(),
                reservation.getUser().getUsername(),
                reservation.getShowing().getHall().getCinema().getId()
        );
    }


}
