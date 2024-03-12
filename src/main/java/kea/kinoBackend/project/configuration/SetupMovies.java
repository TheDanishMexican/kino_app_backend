package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.CinemaRepository;
import kea.kinoBackend.project.repository.HallRepository;
import kea.kinoBackend.project.repository.MovieRepository;
import kea.kinoBackend.project.repository.RowRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SetupMovies implements ApplicationRunner {
    private MovieRepository movieRepository;
    private CinemaRepository cinemaRepository;
    private HallRepository hallRepository;
    private RowRepository rowRepository;

    public SetupMovies(MovieRepository movieRepository, CinemaRepository cinemaRepository, HallRepository hallRepository, RowRepository rowRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
    }

    public void run(ApplicationArguments args) {
        createMovies(); createCinemas();
    }

    public void createMovies() {
         movieRepository.save(new Movie("The Matrix", LocalDate.of(1999, 5, 5), "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. It is the first installment in The Matrix film series, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano."));
         movieRepository.save(new Movie("The Matrix Reloaded", LocalDate.of(2003, 9, 20), "The Matrix Reloaded is a 2003 science fiction action film, the first sequel to The Matrix, and the second installment in The Matrix film series, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Revolutions", LocalDate.of(2003, 2, 17), "The Matrix Revolutions is a 2003 science fiction action film, the third installment of The Matrix trilogy, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Resurrections", LocalDate.of(2021, 7, 11), "The Matrix Resurrections is a 2021 science fiction action film produced, co-written, and directed by Lana Wachowski. It is the fourth installment in The Matrix film series, set twenty years after the events of the third film, The Matrix Revolutions."));
    }

    public void createCinemas() {
        Cinema cinema1 = new Cinema("Central Bio", "Copenhagen");
        cinemaRepository.save(cinema1);

        Hall hall1 = new Hall(cinema1);
        hallRepository.save(hall1);

        Row row1 = new Row(10, 1, hall1, SeatType.COUCH);
        rowRepository.save(row1);

    }
}
