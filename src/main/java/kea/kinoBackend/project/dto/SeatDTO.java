package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record SeatDTO(@JsonIgnore Integer id, int seatNumber, boolean isReserved, int rowId) {}

