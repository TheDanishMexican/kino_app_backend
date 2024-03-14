package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import java.util.Arrays;


@Component
public class SetupMovies implements ApplicationRunner {
    private MovieRepository movieRepository;
    private CinemaRepository cinemaRepository;
    private HallRepository hallRepository;
    private RowRepository rowRepository;
    private ShowingRepository showingRepository;
    private SeatRepository seatRepository;
    private ReservationRepository reservationRepository;

    public SetupMovies(MovieRepository movieRepository, CinemaRepository cinemaRepository, HallRepository hallRepository, RowRepository rowRepository, ShowingRepository showingRepository, SeatRepository seatRepository, ReservationRepository reservationRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    public void run(ApplicationArguments args) {
        createMovies(); createCinemas();
    }

    public void createMovies() {
        Movie testMovie = new Movie(
                "Inception", // name
                "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg", // posterUrl
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
                "https://m.media-amazon.com/images/M/MV5BOWIyN2Y5NmYtMzdjZi00MzcyLWI2NzgtNjM4NmJmZTcwNDJkXkEyXkFqcGdeQXVyNzQyNTU2MjI@._V1_.jpg", // posterUrl
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
                "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg", // posterUrl
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
                "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg", // posterUrl
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

        Cinema cinema2 = new Cinema("West Bio", "Vestegnen");
        cinemaRepository.save(cinema2);

        Hall hall1 = new Hall(cinema1);
        hallRepository.save(hall1);

        Hall hall2 = new Hall(cinema2);
        hallRepository.save(hall2);

        Set<DayOfWeek> weekdays = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

        // Create a new Showing instance with the set of weekdays
        Showing harryPotterAt5 = new Showing(hall1, weekdays, LocalTime.of(17, 0), 177, "Harry Potter");

        Showing theMatrixAt8 = new Showing(hall1, weekdays, LocalTime.of(20, 0), 189, "The Matrix");
        // Save the Showing instance using your repository
        showingRepository.save(theMatrixAt8);
        showingRepository.save(harryPotterAt5);

        Row row1 = new Row(10, 1, hall1, SeatType.COUCH);
        rowRepository.save(row1);

        Row row2 = new Row(10, 1, hall2, SeatType.COWBOY);
        rowRepository.save(row2);

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

        Reservation reservation1 = new Reservation(1, 1, theMatrixAt8);
        reservationRepository.save(reservation1);

        List<Reservation> reservations = reservationRepository.findShowingsByHallID(1);
        harryPotterAt5.setReservations(reservations);
        showingRepository.save(harryPotterAt5);
    }
}
