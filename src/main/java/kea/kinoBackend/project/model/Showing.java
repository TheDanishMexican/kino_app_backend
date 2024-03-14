package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "showings")
@Entity
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Hall hall;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "showing_weekdays", joinColumns = @JoinColumn(name = "showing_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> weekdays;

    private LocalTime startTime;

    private LocalTime endTime;

    private int durationInMinutes;

    private int cinemaId;

    @OneToMany(mappedBy = "showing")
    private List<Reservation> reservations;

    //replace this with the film class when created
    private String filmTitle;

    public Showing() {
    }

    public Showing(Hall hall, Set<DayOfWeek> weekdays, LocalTime startTime, int durationInMinutes, String filmTitle) {
        this.hall = hall;
        this.weekdays = weekdays;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.filmTitle = filmTitle;
        calculateEndTime();
        this.reservations = new ArrayList<>();
        this.cinemaId = hall.getCinema().getId();
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

    public Set<DayOfWeek> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Set<DayOfWeek> weekdays) {
        this.weekdays = weekdays;
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

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
