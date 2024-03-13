package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Table(name = "showings")
@Entity
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Hall hall;

    private LocalDateTime timeAndDate;

    private Duration movieDuration;

    //replace this with the film class when created
    private String filmTitle;

    public Showing() {
    }

    public Showing(Hall hall, LocalDateTime timeAndDate, String filmTitle, Duration movieDuration) {
        this.hall = hall;
        this.timeAndDate = timeAndDate;
        this.filmTitle = filmTitle;
        this.movieDuration = movieDuration;
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

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Duration getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Duration movieDuration) {
        this.movieDuration = movieDuration;
    }
}
