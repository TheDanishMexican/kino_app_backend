package kea.kinoBackend.project.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.kinoBackend.project.model.SeatType;

import java.util.List;

public record RowDTO(@JsonIgnore Integer id, int amountOfSeats, int rowNumber, int hallId, SeatType seatType, List<SeatDTO> seats) {}

