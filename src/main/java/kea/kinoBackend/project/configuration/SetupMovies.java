package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.project.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class SetupMovies implements ApplicationRunner {
    private MovieRepository movieRepository;

    public SetupMovies(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void run(ApplicationArguments args) {
        createMovies();
    }

    public void createMovies() {
        Movie testMovie = new Movie(
                "Inception", // name
                "A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a C.E.O.", // description
                LocalDate.of(2010, 7, 16), // releaseDate
                "Christopher Nolan", // director
                Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), // actors
                Arrays.asList("Action", "Sci-Fi", "Thriller") // genres
        );
        movieRepository.save(testMovie);
    }

}
