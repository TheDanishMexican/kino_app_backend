package kea.kinoBackend.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seatNumber;

    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "seat_row_id", nullable = false)
    private Row row;

    public Seat() {
    }

    public Seat(int seatNumber, boolean isReserved, Row row) {
        this.seatNumber = seatNumber;
        this.isReserved = isReserved;
        this.row = row;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
