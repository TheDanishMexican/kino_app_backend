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
        movieRepository.save(new Movie(
                "Inception",
                "A thief who steals corporate secrets through dream-sharing technology...",
                LocalDate.of(2010, 7, 16),
                "Christopher Nolan",
                Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"),
                Arrays.asList("Action", "Adventure", "Sci-Fi"),
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
        movieRepository.save(new Movie(
                "The Dark Knight",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham...",
                LocalDate.of(2008, 7, 18),
                "Christopher Nolan",
                Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"),
                Arrays.asList("Action", "Crime", "Drama"),
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
        movieRepository.save(new Movie(
                "The Matrix",
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers...",
                LocalDate.of(1999, 3, 31),
                "Lana Wachowski",
                Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"),
                Arrays.asList("Action", "Sci-Fi"),
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
//        movieRepository.save(new Movie(
//                "American Psycho","A wealthy New York City investment banking executive hides his alternate psychopathic ego from his co-workers and friends as he escalates deeper into his illogical, gratuitous fantasies...",LocalDate.of(2000, 4, 14),"Mary Harron",Arrays.asList("Christian Bale", "Justin Theroux", "Josh Lucas"),Arrays.asList("Crime", "Drama", "Thriller"),LocalDateTime.now(),LocalDateTime.now()));
   }
}
