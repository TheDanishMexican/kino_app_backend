package kea.kinoBackend.project.dto;


import kea.kinoBackend.project.model.SeatType;

import java.util.List;

public record RowDTO(int id, int amountOfSeats, int rowNumber, int hallId, SeatType seatType, List<SeatDTO> seats) {}
