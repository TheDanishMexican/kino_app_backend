package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Row;
import kea.kinoBackend.project.repository.RowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RowService {
    private RowRepository rowRepository;

    public RowService(RowRepository rowRepository) {
        this.rowRepository = rowRepository;
    }

    public List<Row> findAll() {
        return rowRepository.findAll();
    }

    public RowDTO toDTO(Row row) {
        return new RowDTO(
                row.getId(),
                row.getAmountOfSeats(),
                row.getRowNumber(),
                row.getHall().getId(),
                row.getSeatType(),
                row.getSeats().stream()
                        .map(seat -> new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.isReserved(), seat.getRow().getId()))
                        .collect(Collectors.toList())
        );
    }
}
