package kea.kinoBackend.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNumber;

    private boolean isReserved;

    private int cinemaId;

    private int hallId;

    @ManyToOne
    @JoinColumn(name = "seat_row_id", nullable = false)
    private Row row;

    public Seat() {
    }

    public Seat(String seatNumber, boolean isReserved, Row row) {
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
        this.row = row;
        this.cinemaId = row.getHall().getCinema().getId();
        this.hallId = row.getHall().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }
}
