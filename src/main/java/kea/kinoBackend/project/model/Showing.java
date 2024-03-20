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

    private boolean specialMovie;

    private boolean isLongMovie;
    private boolean is3dMovie;

    @ManyToOne
    private Movie movie;

    public Showing() {
        this.reservations = new ArrayList<>();
    }

    public Showing(Hall hall, LocalTime startTime, Movie movie, double price, LocalDate showingDate,
                   boolean is3dMovie) {
        this.hall = hall;
        this.startTime = startTime;
        this.durationInMinutes = movie.getDuration();
        this.movie = movie;
        this.price = price;
        calculateEndTime();
        this.cinemaId = hall.getCinema().getId();
        this.showingDate = showingDate;
        this.reservations = new ArrayList<>();
        this.specialMovie = false;
        this.is3dMovie = is3dMovie;
        this.isLongMovie = false;
        makeLongMovie();
        makeSpecialMovie();
    }

    public void calculateEndTime() {
        this.endTime = startTime.plusMinutes(durationInMinutes);
    }

    public void makeLongMovie() {
        if (durationInMinutes > 170) {
            this.isLongMovie = true;
        }
    }

    public void makeSpecialMovie() {
        if (isLongMovie() || is3dMovie) {
            this.specialMovie = true;
        }
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

    public boolean isSpecialMovie() {
        return specialMovie;
    }

    public void setSpecialMovie(boolean specialMovie) {
        this.specialMovie = specialMovie;
    }

    public boolean isLongMovie() {
        return isLongMovie;
    }

    public void setLongMovie(boolean longMovie) {
        isLongMovie = longMovie;
    }

    public boolean isIs3dMovie() {
        return is3dMovie;
    }

    public void setIs3dMovie(boolean is3dMovie) {
        this.is3dMovie = is3dMovie;
    }
}
