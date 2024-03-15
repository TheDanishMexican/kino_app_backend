package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record ReservationDTO (@JsonIgnore Integer id, int customerId, List<SeatDTO> seats, int showingId,
                              int hallId, double totalPrice, double seatPrice){
}
