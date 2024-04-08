package kea.kinoBackend.project.reservation;

import kea.kinoBackend.project.seat.SeatDTO;

import java.util.List;

public class PreReservationInfo {
    private List<SeatDTO> selectedSeats;
    private int showingId;

    // Getters and setters
    public List<SeatDTO> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<SeatDTO> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }
}

