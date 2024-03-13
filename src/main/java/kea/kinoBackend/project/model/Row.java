package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat_rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amountOfSeats;

    private int seatRowNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @OneToMany(mappedBy = "row", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Seat> seats;

    public Row() {
        this.seats = new ArrayList<>();
    }

    public Row(int amountOfSeats, int rowNumber, Hall hall, SeatType seatType) {
        this.amountOfSeats = amountOfSeats;
        this.seatRowNumber = rowNumber;
        this.hall = hall;
        this.seatType = seatType;
        this.seats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public int getRowNumber() {
        return seatRowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.seatRowNumber = rowNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
