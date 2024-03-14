package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Seat;
import kea.kinoBackend.project.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
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
