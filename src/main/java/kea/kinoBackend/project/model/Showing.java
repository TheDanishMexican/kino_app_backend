package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "showings")
@Entity
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Hall hall;

    private LocalDate showingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private int durationInMinutes;

    private int cinemaId;

    private double price;

    @OneToMany(mappedBy = "showing")
    private List<Reservation> reservations;

    @ManyToOne
    private Movie movie;

    public Showing() {
        this.reservations = new ArrayList<>();
    }

    public Showing(Hall hall, LocalTime startTime, Movie movie, double price, LocalDate showingDate) {
        this.hall = hall;
        this.startTime = startTime;
        this.durationInMinutes = movie.getDuration();
        this.movie = movie;
        this.price = price;
        calculateEndTime();
        this.cinemaId = hall.getCinema().getId();
        this.showingDate = showingDate;
        this.reservations = new ArrayList<>();
    }

    public void calculateEndTime() {
        this.endTime = startTime.plusMinutes(durationInMinutes);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getShowingDate() {
        return showingDate;
    }

    public void setShowingDate(LocalDate showingDate) {
        this.showingDate = showingDate;
    }
}
