package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Row;
import kea.kinoBackend.project.repository.HallRepository;
import kea.kinoBackend.project.repository.RowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RowService {
    private RowRepository rowRepository;
    private HallRepository hallRepository;

    public RowService(RowRepository rowRepository, HallRepository hallRepository) {
        this.rowRepository = rowRepository;
        this.hallRepository = hallRepository;
    }

    /**
     * Get all rows
     * @return a list of all rows
     */
    public List<RowDTO> getAllRows() {
        List<Row> rows = rowRepository.findAll();
        List<RowDTO> rowResponses = rows.stream().map(this::toDTO).toList();

        return rowResponses;
    }

    /**
     * Get row by id
     * @param id - the id of the row
     * @return the row with the given id
     */
    public RowDTO getRowById(int id) {
        Row row = rowRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Row not found"));
        return toDTO(row);
    }

    /**
     * Add a new row
     * @param request - the row to add
     * @return the added row
     */

    public RowDTO addRow(RowDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new row");
        }
        Row newRow = new Row();
        updateRow(newRow, request);
        rowRepository.save(newRow);
        return toDTO(newRow);
    }

    public void updateRow(Row original, RowDTO request) {
        original.setAmountOfSeats(request.amountOfSeats());
        original.setRowNumber(request.rowNumber());
        original.setHall(hallRepository.findById(request.hallId()).orElseThrow(() -> new IllegalArgumentException("Hall not found")));
        original.setSeatType(request.seatType());
    }

    /**
     * Edit a row
     * @param request - the new row data
     * @param id - the id of the row to edit
     * @return the edited row
     */
    public RowDTO editRow(RowDTO request, int id) {
        Row row = rowRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Row not found"));
        updateRow(row, request);
        rowRepository.save(row);
        return toDTO(row);
    }

    /**
     * Deletes the row with the given id
     * @param id - the id of the row to delete
     * @return - a response entity with No Content status
     */
    public ResponseEntity deleteRow(int id) {
        Row row = rowRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Row not found"));
        rowRepository.delete(row);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    public RowDTO toDTO(Row row) {
        return new RowDTO(
                row.getId(),
                row.getAmountOfSeats(),
                row.getRowNumber(),
                row.getHall().getId(),
                row.getSeatType(),
                row.getSeats().stream()
                        .map(seat -> new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.getRow().getId()))
                        .collect(Collectors.toList())
        );
    }
}
