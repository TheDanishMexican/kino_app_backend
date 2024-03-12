package kea.kinoBackend.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Row> rows;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Showing> showings;

    public Hall() {
        this.rows = new ArrayList<>();
        this.showings = new ArrayList<>();
    }

    public Hall(Cinema cinema) {
        this.cinema = cinema;
        this.rows = new ArrayList<>();
        this.showings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
}
