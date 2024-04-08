package kea.kinoBackend.project.row;


import kea.kinoBackend.project.seat.SeatDTO;
import kea.kinoBackend.project.seat.SeatType;

import java.util.List;

public record RowDTO(Integer id, int amountOfSeats, int rowNumber, int hallId, SeatType seatType, List<SeatDTO> seats) {}

