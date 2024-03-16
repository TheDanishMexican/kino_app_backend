package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowingService {
    private ShowingRepository showingRepository;
    private HallRepository hallRepository;
    private ReservationService reservationService;
    private SeatRepository seatRepository;
    private RowRepository rowRepository;
    private MovieRepository movieRepository;
    private SeatService seatService;

    public ShowingService(ShowingRepository showingRepository, HallRepository hallRepository,
                          ReservationService reservationService, SeatRepository seatRepository,
                          RowRepository rowRepository, MovieRepository movieRepository, SeatService seatService) {
        this.showingRepository = showingRepository;
        this.hallRepository = hallRepository;
        this.reservationService = reservationService;
        this.seatRepository = seatRepository;
        this.rowRepository = rowRepository;
        this.movieRepository = movieRepository;
        this.seatService = seatService;
    }

    public List<ShowingDTO> findAllShowings() {
        List<Showing> showings = showingRepository.findAll();
        List<ShowingDTO> showingResponses = showings.stream().map(this::toDTO).toList();

        return showingResponses;
    }

    public ShowingDTO getShowingById(int id) {
        Showing showing = showingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Showing not found"));
        return toDTO(showing);
    }

    public ShowingDTO addShowing(ShowingDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new showing");
        }

        Showing newShowing = new Showing();
        updateShowing(newShowing, request);
        showingRepository.save(newShowing);
        return toDTO(newShowing);
    }

    public void updateShowing(Showing original, ShowingDTO request) {
        original.setStartTime(request.startTime());
        original.setMovie(movieRepository.findById(request.movieId()).orElseThrow(() ->
                new IllegalArgumentException("Movie not found")));
        original.setHall(hallRepository.findById(request.hallId()).orElseThrow(() ->
                new IllegalArgumentException("Hall not found")));
        original.setDurationInMinutes(original.getMovie().getDuration());
        original.setPrice(request.price());
        original.setCinemaId(request.cinemaId());
        original.setShowingDate(request.showingDate());
        if (original.getEndTime() == null) {
            original.calculateEndTime();
        }
    }

    public ShowingDTO editShowing(ShowingDTO request, int id) {
        Showing showing = showingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Showing not found"));

        updateShowing(showing, request);
        showingRepository.save(showing);
        return toDTO(showing);
    }

    public ResponseEntity deleteShowing(int id) {
        showingRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public double getSeatPriceFromShowing(int showingId, int seatId) {
        double seatPrice = 0;

        double showingPrice = showingRepository.findById(showingId).orElseThrow(() ->
                new IllegalArgumentException("Showing not found")).getPrice();

        Seat seat = seatRepository.findById(seatId).orElseThrow(() ->
                new IllegalArgumentException("Seat not found"));

        SeatType seatType = seat.getRow().getSeatType();

            switch (seatType) {
                case COUCH:
                    seatPrice += showingPrice * 0.8;
                    break;
                case STANDARD:
                    seatPrice += showingPrice;
                    break;
                case COWBOY:
                    seatPrice += showingPrice * 1.2;
                    break;
            }
            return seatPrice;
        }

        public List<SeatDTO> getSeatsInShowing(int showingId) {
            Hall hall = hallRepository.findById((showingRepository.findById(showingId)).orElseThrow(() ->
                    new IllegalArgumentException("Showing not found")).getHall().getId()).orElseThrow(() ->
                    new IllegalArgumentException("Hall not found"));

            List<Seat> seats = seatRepository.findAllByCinemaIdAndHallId(hall.getCinema().getId(), hall.getId());

            return seats.stream().map(seatService::toDTO).toList();
        }

        public List<SeatDTO> getReservedSeatsInShowing(int showingId) {
            List<Seat> reservedSeats = new ArrayList<>();

            List<Reservation> reservations = showingRepository.findById(showingId).orElseThrow(() ->
                    new IllegalArgumentException("Showing not found")).getReservations();

            for (Reservation reservation : reservations) {
                reservedSeats.addAll(reservation.getSeats());
            }

            return reservedSeats.stream().map(seatService::toDTO).toList();
        }



    public ShowingDTO toDTO(Showing showing) {
        List<ReservationDTO> reservationDTOs = showing.getReservations().stream()
                .map(reservationService::toDTO)
                .toList();

        return new ShowingDTO(
                showing.getId(),
                showing.getHall().getId(),
                showing.getStartTime(),
                showing.getEndTime(),
                showing.getMovie().getId(),
                showing.getDurationInMinutes(),
                reservationDTOs,
                showing.getPrice(),
                showing.getHall().getCinema().getId(),
                showing.getShowingDate()

        );
    }
}
