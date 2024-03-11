package kea.kinobackend.project.configuration;

import kea.kinobackend.project.model.Movie;
import kea.kinobackend.project.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
         movieRepository.save(new Movie("The Matrix", LocalDate.of(1999, 5, 5), "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. It is the first installment in The Matrix film series, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano."));
         movieRepository.save(new Movie("The Matrix Reloaded", LocalDate.of(2003, 9, 20), "The Matrix Reloaded is a 2003 science fiction action film, the first sequel to The Matrix, and the second installment in The Matrix film series, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Revolutions", LocalDate.of(2003, 2, 17), "The Matrix Revolutions is a 2003 science fiction action film, the third installment of The Matrix trilogy, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Resurrections", LocalDate.of(2021, 7, 11), "The Matrix Resurrections is a 2021 science fiction action film produced, co-written, and directed by Lana Wachowski. It is the fourth installment in The Matrix film series, set twenty years after the events of the third film, The Matrix Revolutions."));
    }
}
