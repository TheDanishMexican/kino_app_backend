package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Seat;
import kea.kinoBackend.project.repository.RowRepository;
import kea.kinoBackend.project.repository.SeatRepository;
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

    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        List<SeatDTO> seatResponses = seats.stream().map(this::toDTO).toList();

        return seatResponses;
    }

    public SeatDTO getSeatById(int id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        return toDTO(seat);
    }

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
        original.setReserved(request.isReserved());
        original.setRow(rowRepository.findById(request.rowId()).orElseThrow(() -> new IllegalArgumentException("Row not found")));
    }

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


    public SeatDTO toDTO(Seat seat) {
        return new SeatDTO(
                seat.getId(),
                seat.getSeatNumber(),
                seat.isReserved(),
                seat.getRow().getId()
        );
    }
}
