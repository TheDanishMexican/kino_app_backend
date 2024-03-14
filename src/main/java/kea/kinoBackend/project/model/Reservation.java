package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;


    @OneToMany
    private List<Seat> seats;

    @ManyToOne
    private Showing showing;

    private int hallId;

    private int cinemaId;

    public Reservation() {
    }

    public Reservation(int userId, List<Seat> seats, Showing showing) {
        this.userId = userId;
        this.seats = seats;
        this.showing = showing;
        this.hallId = showing.getHall().getId();
        markSeatsAsReserved();
        this.cinemaId = showing.getHall().getCinema().getId();
    }

    private void markSeatsAsReserved() {
        for (Seat seat : seats) {
            seat.setReserved(true);
        }
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
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

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
