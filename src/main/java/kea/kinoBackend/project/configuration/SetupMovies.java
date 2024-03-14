package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Arrays;


@Component
public class SetupMovies implements ApplicationRunner {
    private MovieRepository movieRepository;
    private CinemaRepository cinemaRepository;
    private HallRepository hallRepository;
    private RowRepository rowRepository;
    private ShowingRepository showingRepository;
    private SeatRepository seatRepository;

    public SetupMovies(MovieRepository movieRepository, CinemaRepository cinemaRepository, HallRepository hallRepository, RowRepository rowRepository, ShowingRepository showingRepository, SeatRepository seatRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
    }

    public void run(ApplicationArguments args) {
        createMovies(); createCinemas();
    }

    public void createMovies() {
        Movie testMovie = new Movie(
                "Inception", // name
                "A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a C.E.O.", // description
                148, // duration
                LocalDate.of(2010, 7, 16), // releaseDate
                "Christopher Nolan", // director
                Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"), // actors
                Arrays.asList("Action", "Sci-Fi", "Thriller") // genres
        );
        movieRepository.save(testMovie);

        Movie Barbie = new Movie(
                "Barbie", // name
                "A Barbie movie", // description
                90, // duration
                LocalDate.of(2021, 1, 1), // releaseDate
                "Barbie", // director
                Arrays.asList("Barbie", "Ken", "Skipper"), // actors
                Arrays.asList("Animation", "Family", "Fantasy") // genres
        );

        movieRepository.save(Barbie);

        Movie testMovie2 = new Movie(
                "The Dark Knight", // name
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", // description
                152, // duration
                LocalDate.of(2008, 7, 18), // releaseDate
                "Christopher Nolan", // director
                Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart"), // actors
                Arrays.asList("Action", "Crime", "Drama") // genres
        );

        movieRepository.save(testMovie2);

        Movie testMovie3 = new Movie(
                "The Shawshank Redemption", // name
                "Two imprisoned", // description
                120, // duration
                LocalDate.of(1994, 10, 14), // releaseDate
                "Frank Darabont", // director
                Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), // actors
                Arrays.asList("Drama", "blop") // genres
        );
        movieRepository.save(testMovie3);

    }

    public void createCinemas() {


        Cinema cinema1 = new Cinema("Central Bio", "Copenhagen");
        cinemaRepository.save(cinema1);

        Hall hall1 = new Hall(cinema1);
        hallRepository.save(hall1);

        Showing HarryPotterAt5 = new Showing(hall1, LocalDateTime.of(2022, 5, 5, 17, 0), "Harry Potter");
        showingRepository.save(HarryPotterAt5);

        Row row1 = new Row(10, 1, hall1, SeatType.COUCH);
        rowRepository.save(row1);

        Seat seat1 = new Seat(1, false, row1);
        seatRepository.save(seat1);

        Seat seat2 = new Seat(2, false, row1);
        seatRepository.save(seat2);

        row1.setSeats(seatRepository.findAll());
        rowRepository.save(row1);

        hall1.setRows(rowRepository.findAll());
        hallRepository.save(hall1);

        cinema1.setHalls(hallRepository.findAll());
        cinemaRepository.save(cinema1);
    }
}
