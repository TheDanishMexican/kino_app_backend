package kea.kinoBackend.project.seat;

import kea.kinoBackend.project.seat.SeatDTO;
import kea.kinoBackend.project.row.Row;
import kea.kinoBackend.project.row.RowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private SeatRepository seatRepository;
    private RowRepository rowRepository;

    public SeatService(SeatRepository seatRepository, RowRepository rowRepository) {
        this.seatRepository = seatRepository;
        this.rowRepository = rowRepository;
    }

    /**
     * Get all seats
     * @return a list of all seats
     */

    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        List<SeatDTO> seatResponses = seats.stream().map(this::toDTO).toList();

        return seatResponses;
    }

    /**
     * Get seat by id
     * @param id - the id of the seat
     * @return  the seat with the given id
     */
    public SeatDTO getSeatById(int id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        return toDTO(seat);
    }

    /**
     * Add a new seat
     * @param request - the seat to add
     * @return the added seat
     */
    public SeatDTO addSeat(SeatDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new seat");
        }

        Optional<Seat> existingSeat = seatRepository.findByRowIdAndSeatNumber(request.rowId(), request.seatNumber());
        if (existingSeat.isPresent()) {
            throw new IllegalArgumentException("A seat with the same seat number already exists in the row");
        }

        Seat newSeat = new Seat();
        updateSeat(newSeat, request);
        seatRepository.save(newSeat);
        return toDTO(newSeat);
    }

    public void updateSeat(Seat original, SeatDTO request) {
        original.setSeatNumber(request.seatNumber());
        original.setRow(rowRepository.findById(request.rowId()).orElseThrow(() -> new IllegalArgumentException("Row not found")));
    }

    /**
     * Edit a seat
     * @param request - the new seat data
     * @param id - the id of the seat to edit
     * @return the edited seat
     */
    public SeatDTO editSeat(SeatDTO request, int id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        // Tjekker om seatNumber er det samme som det i SeatDTO (Den der bruges til at opdatere),
        // hvis ikke så betyder det at seatNumber bliver opdateret og at den går videre ind i if statementet
        if (request.seatNumber() != seat.getSeatNumber()) {
            // Tjekker om der findes et sæde med det samme seatNumber i den samme række
            Optional<Seat> existingSeat = seatRepository.findByRowIdAndSeatNumber(seat.getRow().getId(), request.seatNumber());
            if (existingSeat.isPresent()) {
                throw new IllegalArgumentException("A seat with the same seat number already exists in the row");
            }
        }

        updateSeat(seat, request);
        seatRepository.save(seat);
        return toDTO(seat);
    }

    public ResponseEntity deleteSeat(int id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        seatRepository.delete(seat);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public Row getRowBySeatId(int id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        return seat.getRow();
    }

    public SeatDTO toDTO(Seat seat) {
        return new SeatDTO(
                seat.getId(),
                seat.getSeatNumber(),
                seat.getRow().getId()
        );
    }
}
