package kea.kinoBackend.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int seatId;

    @ManyToOne
    private Showing showing;

    private int hallId;

    public Reservation() {
    }

    public Reservation(int userId, int seatId, Showing showing) {
        this.userId = userId;
        this.seatId = seatId;
        this.showing = showing;
        this.hallId = showing.getHall().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int customerID) {
        this.userId = customerID;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatID) {
        this.seatId = seatID;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public int getHallId() {
        return hallId;
    }
}
