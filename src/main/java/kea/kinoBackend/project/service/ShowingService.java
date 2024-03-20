package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.dto.RowDTO;
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
    private RowService rowService;

    public ShowingService(ShowingRepository showingRepository, HallRepository hallRepository,
                          ReservationService reservationService, SeatRepository seatRepository,
                          RowRepository rowRepository, MovieRepository movieRepository, SeatService seatService, RowService rowService) {
        this.showingRepository = showingRepository;
        this.hallRepository = hallRepository;
        this.reservationService = reservationService;
        this.seatRepository = seatRepository;
        this.rowRepository = rowRepository;
        this.movieRepository = movieRepository;
        this.seatService = seatService;
        this.rowService = rowService;
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
        original.setMovie(movieRepository.findById(request.movie().getId()).orElseThrow(() ->
                new IllegalArgumentException("Movie not found")));
        original.setHall(hallRepository.findById(request.hallId()).orElseThrow(() ->
                new IllegalArgumentException("Hall not found")));
        original.setDurationInMinutes(original.getMovie().getDuration());
        original.setPrice(request.price());
        original.setCinemaId(request.cinemaId());
        original.setShowingDate(request.showingDate());
        original.setIs3dMovie(request.is3dMovie());
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
                    seatPrice += showingPrice * 1.2;
                    break;
                case STANDARD:
                    seatPrice += showingPrice;
                    break;
                case COWBOY:
                    seatPrice += showingPrice * 0.8;
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

        public int getCinemaId(int showingId) {
            return showingRepository.findById(showingId).orElseThrow(() ->
                    new IllegalArgumentException("Showing not found")).getHall().getCinema().getId();
        }


        public List<RowDTO> getRowsInShowing(int showingId) {
            Hall hall = hallRepository.findById((showingRepository.findById(showingId)).orElseThrow(() ->
                    new IllegalArgumentException("Showing not found")).getHall().getId()).orElseThrow(() ->
                    new IllegalArgumentException("Hall not found"));

            List<Row> rows = rowRepository.findAllByHallId(hall.getId());

            return rows.stream().map(rowService::toDTO).toList();
        }

    public double reservationPrice(List<SeatDTO> selectedSeats, int showingId) {
        Showing showing = showingRepository.findById(showingId).orElseThrow(() ->
                new IllegalArgumentException("Showing not found"));
        double price = 0;
        double fee = 50;
        double discountRate = 0.93;
        double discountThreshold = 10;

        for (SeatDTO seat : selectedSeats) {
            price += getSeatPriceFromShowing(showingId, seat.id());
        }

        if (selectedSeats.size() <= 5) {  // Apply fee for 5 or fewer reservations
            price += fee;
        } else if (selectedSeats.size() >= discountThreshold) {  // Apply discount for 10 or more reservations
            price *= discountRate;
        }

        if (showing.isSpecialMovie()) {
            price += 50;
        }

        return price;
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
                showing.getMovie(),
                showing.getDurationInMinutes(),
                reservationDTOs,
                showing.getPrice(),
                showing.getHall().getCinema().getId(),
                showing.getShowingDate(),
                showing.isSpecialMovie(),
                showing.isIs3dMovie()
        );
    }
}
