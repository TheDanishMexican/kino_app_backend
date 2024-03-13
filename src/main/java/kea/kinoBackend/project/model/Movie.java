package kea.kinoBackend.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
//    @Column(unique = true)
    @Id
    private String name;

    private String description;

    private LocalDate releaseDate;

    private String director;

    @ElementCollection
    private List<String> actors;

    @ElementCollection //laver vidst en ny tabel for genres kryds tabel type vibes
    private List<String> genres;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;



}
