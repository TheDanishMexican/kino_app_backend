package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ReservationDTO (@JsonIgnore Integer id, int customerId, int seatId, int showingId, int hallId){
}
