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

    public ReservationService(ReservationRepository reservationRepository, SeatService seatService,
                              ShowingRepository showingRepository, SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.seatService = seatService;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationResponses = reservations.stream().map(this::toDTO).toList();

        return reservationResponses;
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return toDTO(reservation);
    }

//    public RowDTO addRow(RowDTO request) {
//        if (request.id() != null) {
//            throw new IllegalArgumentException("You cannot provide the id for a new row");
//        }
//        Row newRow = new Row();
//        updateRow(newRow, request);
//        rowRepository.save(newRow);
//        return toDTO(newRow);
//    }
//
//    public void updateRow(Row original, RowDTO request) {
//        original.setAmountOfSeats(request.amountOfSeats());
//        original.setRowNumber(request.rowNumber());
//        original.setHall(hallRepository.findById(request.hallId()).orElseThrow(() -> new IllegalArgumentException("Hall not found")));
//        original.setSeatType(request.seatType());
//    }

    public ReservationDTO addReservation(ReservationDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new reservation");
        }
        Reservation newReservation = new Reservation();
        updateReservation(newReservation, request);
        reservationRepository.save(newReservation);
        return toDTO(newReservation);
    }

    public void updateReservation(Reservation original, ReservationDTO request) {

        original.setUserId(request.id());
        original.setSeats(request.seats().stream()
                .map(seat -> seatRepository.findById(seat.id()).orElseThrow(() ->
                        new IllegalArgumentException("Seat not found")))
                .collect(Collectors.toList()));
        original.setShowing(showingRepository.findById(request.showingId()).orElseThrow(() ->
                new IllegalArgumentException("Showing not found")));
        original.setHallId(request.hallId());
        original.setTotalPrice(request.totalPrice());
        original.setSeatPrice(request.seatPrice());
        original.setUser(userRepository.findById(request.userName()).orElseThrow(() ->
                new IllegalArgumentException("User not found")));
    }

    public ResponseEntity deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationRepository.delete(reservation);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ReservationDTO toDTO(Reservation reservation) {
        List<SeatDTO> seatDTOs = reservation.getSeats().stream().map(seatService::toDTO).toList();

        return new ReservationDTO(
                reservation.getId(),
                reservation.getUserId(),
                seatDTOs,
                reservation.getShowing().getId(),
                reservation.getHallId(),
                reservation.getTotalPrice(),
                reservation.getSeatPrice(),
                reservation.getUser().getUsername()
        );
    }


}
