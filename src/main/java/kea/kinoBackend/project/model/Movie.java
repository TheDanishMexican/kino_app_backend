package kea.kinoBackend.project.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String posterUrl;
    private String description;

    private LocalDate releaseDate;

    private String director;

    private int duration;

    @ElementCollection
    private List<String> actors;

    @ElementCollection
    private List<String> genres;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    public Movie(String name, String posterUrl, String description, int duration, LocalDate releaseDate, String director, List<String> actors, List<String> genres) {
        this.name = name;
        this.posterUrl = posterUrl;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
    }

}
