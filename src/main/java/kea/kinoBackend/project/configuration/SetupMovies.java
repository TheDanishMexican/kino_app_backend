package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import kea.kinoBackend.security.entity.UserWithRoles;
import kea.kinoBackend.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Component
public class SetupMovies implements ApplicationRunner {
    private MovieRepository movieRepository;
    private CinemaRepository cinemaRepository;
    private HallRepository hallRepository;
    private RowRepository rowRepository;
    private ShowingRepository showingRepository;
    private SeatRepository seatRepository;
    private ReservationRepository reservationRepository;
    private UserWithRolesRepository UserWithRolesRepository;
    private PasswordEncoder passwordEncoder;

    public SetupMovies(MovieRepository movieRepository, CinemaRepository cinemaRepository, HallRepository hallRepository, RowRepository rowRepository, ShowingRepository showingRepository, SeatRepository seatRepository, ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
        UserWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        createMovies(); createCinemas(); createUserForTesting();
    }

    //HELPER METHOD TO CREATE SEATS FAST FOR A ROW,
    // THE LETTER IS BASED ON THE ROW, SO ROW 1 WILL HAVE A AS SEAT LETTER FX
    public void createSeats(Row row, String seatLetter) {
        for (int i = 1; i<= 10; i++) {
            Seat seat = new Seat(i + seatLetter, false, row);
            seatRepository.save(seat);
        }
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
        //MOVIES TO USE IN THE CINEMA
        Movie barbie = movieRepository.findByNameLike("Barbie");
        Movie inception = movieRepository.findByNameLike("Inception");
        Movie batman = movieRepository.findByNameLike("The Dark Knight");


        //CREATED CENTRALBIO CINEMA
        Cinema centralBio = new Cinema("Central Bio", "Copenhagen");
        cinemaRepository.save(centralBio);

        //CREATED HALLS FOR CENTRALBIO
        Hall hall1CentralBio = new Hall(centralBio);
        Hall hall2CentralBio = new Hall(centralBio);
        Hall hall3CentralBio = new Hall(centralBio);
        Hall hall4CentralBio = new Hall(centralBio);

        hallRepository.save(hall1CentralBio);
        hallRepository.save(hall2CentralBio);
        hallRepository.save(hall3CentralBio);
        hallRepository.save(hall4CentralBio);

        //CREATED ROWS FOR HALL1 IN CENTRALBIO
        Row row1Hall1CentralBio = new Row(10, 1, hall1CentralBio, SeatType.COWBOY);
        Row row2Hall1CentralBio = new Row(10, 2, hall1CentralBio, SeatType.STANDARD);
        Row row3Hall1CentralBio = new Row(10, 3, hall1CentralBio, SeatType.STANDARD);
        Row row4Hall1CentralBio = new Row(10, 4, hall1CentralBio, SeatType.STANDARD);
        Row row5Hall1CentralBio = new Row(10, 5, hall1CentralBio, SeatType.COUCH);
        Row row6Hall1CentralBio = new Row(10, 6, hall1CentralBio, SeatType.COUCH);

        List<Row> rows = List.of(row1Hall1CentralBio, row2Hall1CentralBio, row3Hall1CentralBio, row4Hall1CentralBio, row5Hall1CentralBio, row6Hall1CentralBio);
        rowRepository.saveAll(rows);

        //CREATED SEATS FOR ROW1 IN HALL1 IN CENTRALBIO

        createSeats(row1Hall1CentralBio, "A");
        createSeats(row2Hall1CentralBio, "B");
        createSeats(row3Hall1CentralBio, "C");
        createSeats(row4Hall1CentralBio, "D");
        createSeats(row5Hall1CentralBio, "E");
        createSeats(row6Hall1CentralBio, "F");


        //SHOWINGS FOR HALL1 IN CENTRALBIO
        Set<DayOfWeek> allWeek = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        Showing barbieAt5Hall1 = new Showing(hall1CentralBio, LocalTime.of(16, 10), barbie, 100);
        Showing inceptionAt8Hall1 = new Showing(hall1CentralBio, LocalTime.of(19, 30), inception, 100);
        Showing batmanAt11Hall1 = new Showing(hall1CentralBio, LocalTime.of(11, 35 ), batman, 100);

        List<Showing> showings = List.of(barbieAt5Hall1, inceptionAt8Hall1, batmanAt11Hall1);
        showingRepository.saveAll(showings);

        //LISTS OF SEATS FOR RESERVATIONS
        List<Seat> seatsForReservation1 = List.of(seatRepository.findBySeatNumber("1A"), seatRepository.findBySeatNumber("2A"), seatRepository.findBySeatNumber("3A"));
        List<Seat> seatsForReservation2 = List.of(seatRepository.findBySeatNumber("1B"), seatRepository.findBySeatNumber("2B"), seatRepository.findBySeatNumber("3B"));
        List<Seat> seatsForReservation3 = List.of(seatRepository.findBySeatNumber("1C"), seatRepository.findBySeatNumber("2C"), seatRepository.findBySeatNumber("3C"));
        List<Seat> seatsForReservation4 = List.of(seatRepository.findBySeatNumber("1D"), seatRepository.findBySeatNumber("2D"), seatRepository.findBySeatNumber("3D"));
        List<Seat> seatsForReservation5 = List.of(seatRepository.findBySeatNumber("1E"), seatRepository.findBySeatNumber("2E"), seatRepository.findBySeatNumber("3E"));

        //RESERVATIONS FOR SHOWINGS IN HALL1 CENTRALBIO
        Reservation reservation1 = new Reservation(1, seatsForReservation1,barbieAt5Hall1);
        Reservation reservation2 = new Reservation(2, seatsForReservation2,barbieAt5Hall1);
        Reservation reservation3 = new Reservation(3, seatsForReservation3,barbieAt5Hall1);
        Reservation reservation4 = new Reservation(4, seatsForReservation4,barbieAt5Hall1);
        Reservation reservation5 = new Reservation(5, seatsForReservation5,barbieAt5Hall1);

        List<Reservation> reservations = List.of(reservation1, reservation2, reservation3, reservation4, reservation5);
        reservationRepository.saveAll(reservations);
    }

    public void createUserForTesting() {
        UserWithRoles user1 = new UserWithRoles("user1", passwordEncoder.encode("password"), "test@email.com");
        UserWithRolesRepository.save(user1);
    }

}
