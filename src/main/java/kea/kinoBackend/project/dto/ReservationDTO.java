package kea.kinoBackend.project.dto;

import java.util.List;

public record ReservationDTO (Integer id, List<SeatDTO> seats, int showingId,
                              int hallId, double totalPrice, double seatPrice, String userName, int cinemaId){
}
