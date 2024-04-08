package kea.kinoBackend.project.reservation;

import kea.kinoBackend.project.seat.SeatDTO;
import lombok.Getter;

import java.util.List;

public record ReservationDTO (Integer id, List<SeatDTO> seats, int showingId,
                              int hallId, double totalPrice, double seatPrice, @Getter String username, int cinemaId){
}
