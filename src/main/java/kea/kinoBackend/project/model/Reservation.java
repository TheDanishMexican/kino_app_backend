package kea.kinoBackend.project.model;

import jakarta.persistence.*;
import kea.kinoBackend.security.entity.UserWithRoles;

import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Seat> seats;

    @ManyToOne
    private Showing showing;

    private int hallId;

    private int cinemaId;

    private double totalPrice;

    private double seatPrice;

    @ManyToOne
    private UserWithRoles user;

    public Reservation() {
    }

    public Reservation(List<Seat> seats, Showing showing, UserWithRoles user) {
        this.seats = seats;
        this.showing = showing;
        this.hallId = showing.getHall().getId();
        this.cinemaId = showing.getHall().getCinema().getId();
        this.totalPrice = calculateTotalPrice();
        this.seatPrice = calculateSeatPrice();
        this.user = user;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Seat seat : seats) {
            switch(seat.getRow().getSeatType()) {
                case COUCH:
                    totalPrice += showing.getPrice() * 0.8;
                    break;
                case STANDARD:
                    totalPrice += showing.getPrice();
                    break;
                case COWBOY:
                    totalPrice += showing.getPrice() * 1.2;
                    break;
            }
        }
        return totalPrice;
    }

    public double calculateSeatPrice() {
       return totalPrice / seats.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public UserWithRoles getUser() {
        return user;
    }

    public void setUser(UserWithRoles user) {
        this.user = user;
    }

}
