package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import kea.kinoBackend.security.entity.Role;
import kea.kinoBackend.security.entity.UserWithRoles;
import kea.kinoBackend.security.repository.RoleRepository;
import kea.kinoBackend.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


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
    private RoleRepository roleRepository;

    public SetupMovies(MovieRepository movieRepository, CinemaRepository cinemaRepository, HallRepository hallRepository,
                       RowRepository rowRepository, ShowingRepository showingRepository, SeatRepository seatRepository,
                       ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository,
                       PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.rowRepository = rowRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
        UserWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        createMovies(); createCinema1(); createCinema2();
    }

    //HELPER METHOD TO CREATE SEATS FAST FOR A ROW,
    // THE LETTER IS BASED ON THE ROW, SO ROW 1 WILL HAVE A AS SEAT LETTER FX
    public void createSeats(Row row, String seatLetter, int amountOfSeats) {
        for (int i = 1; i<= amountOfSeats; i++) {
            Seat seat = new Seat(i + seatLetter, row);
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

        Movie testMovie4 = new Movie(
                "The Big Short", // name
                "https://m.media-amazon.com/images/I/91dC4o8mScL._AC_UF894,1000_QL80_.jpg", // posterUrl
                "In 2006-2007 a group of investors bet against the US mortgage market. In their research they discover how flawed and corrupt the market is.", // description
                130, // duration
                LocalDate.of(2015, 12, 23), // releaseDate
                "Adam McKay", // director
                Arrays.asList("Christian Bale", "Steve Carell", "Ryan Gosling"), // actors
                Arrays.asList("Biography", "Comedy", "Drama") // genres
        );

        movieRepository.save(testMovie4);

        Movie testMovie5 = new Movie(
                "The Wolf of Wall Street", // name
                "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p9991602_p_v12_aj.jpg", // posterUrl
                "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", // description
                180, // duration
                LocalDate.of(2013, 12, 25), // releaseDate
                "Martin Scorsese", // director
                Arrays.asList("Leonardo DiCaprio", "Jonah Hill", "Margot Robbie"), // actors
                Arrays.asList("Biography", "Comedy", "Crime") // genres
        );
        movieRepository.save(testMovie5);

        Movie testMovie6 = new Movie(
                "Star Wars", // name
                "https://m.media-amazon.com/images/M/MV5BOTAzODEzNDAzMl5BMl5BanBnXkFtZTgwMDU1MTgzNzE@._V1_FMjpg_UX1000_.jpg", // posterUrl
                "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station," +
                        " while also attempting to rescue Princess Leia from the mysterious Darth Vader.", // description
                121, // duration
                LocalDate.of(1977, 5, 25), // releaseDate
                "George Lucas", // director
                Arrays.asList("Mark Hamill", "Harrison Ford", "Carrie Fisher"), // actors
                Arrays.asList("Action", "Adventure", "Fantasy") // genres
        );
        movieRepository.save(testMovie6);

        Movie testMovie7 = new Movie(
                "The Godfather", // name
                "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg", // posterUrl
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", // description
                175, // duration
                LocalDate.of(1972, 3, 24), // releaseDate
                "Francis Ford Coppola", // director
                Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), // actors
                Arrays.asList("Crime", "Drama") // genres
        );

        movieRepository.save(testMovie7);

        Movie testMovie8 = new Movie(
                "Pulp Fiction", // name
                "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15684_p_v10_ag.jpg", // posterUrl
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", // description
                154, // duration
                LocalDate.of(1994, 10, 14), // releaseDate
                "Quentin Tarantino", // director
                Arrays.asList("John Travolta", "Uma Thurman", "Samuel L. Jackson"), // actors
                Arrays.asList("Crime", "Drama") // genres
        );

        movieRepository.save(testMovie8);

        Movie testMovie9 = new Movie(
                "The Matrix", // name
                "https://m.media-amazon.com/images/M/MV5BODE0MzZhZTgtYzkwYi00YmI5LThlZWYtOWRmNWE5ODk0NzMxXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg", // posterUrl
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", // description
                136, // duration
                LocalDate.of(1999, 3, 31), // releaseDate
                "Lana Wachowski", // director
                Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"), // actors
                Arrays.asList("Action", "Sci-Fi") // genres
        );

        movieRepository.save(testMovie9);

        Movie testMovie10 = new Movie(
                "The Lord of the Rings", // name
                "https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg", // posterUrl
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", // description
                178, // duration
                LocalDate.of(2001, 12, 19), // releaseDate
                "Peter Jackson", // director
                Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"), // actors
                Arrays.asList("Action", "Adventure", "Drama") // genres
        );

        movieRepository.save(testMovie10);
    }

    public void createCinema1() {
        //MOVIES TO USE IN THE CINEMA
        Movie barbie = movieRepository.findByNameLike("Barbie");
        Movie inception = movieRepository.findByNameLike("Inception");
        Movie batman = movieRepository.findByNameLike("The Dark Knight");
        Movie shawshank = movieRepository.findByNameLike("The Shawshank Redemption");
        Movie bigShort = movieRepository.findByNameLike("The Big Short");
        Movie wolfOfWallStreet = movieRepository.findByNameLike("The Wolf of Wall Street");
        Movie starWars = movieRepository.findByNameLike("Star Wars");
        Movie godfather = movieRepository.findByNameLike("The Godfather");

        //CREATED CENTRALBIO CINEMA
        Cinema centralBio = new Cinema("Central Bio", "Copenhagen");
        cinemaRepository.save(centralBio);

        //CREATED HALLS FOR CENTRALBIO
        Hall hall1CentralBio = new Hall(centralBio);

        hallRepository.save(hall1CentralBio);

        //CREATED ROWS FOR HALL1 IN CENTRALBIO
        Row row1Hall1CentralBio = new Row(12, 1, hall1CentralBio, SeatType.COWBOY);
        Row row2Hall1CentralBio = new Row(12, 2, hall1CentralBio, SeatType.COWBOY);
        Row row3Hall1CentralBio = new Row(14, 3, hall1CentralBio, SeatType.STANDARD);
        Row row4Hall1CentralBio = new Row(14, 4, hall1CentralBio, SeatType.STANDARD);
        Row row5Hall1CentralBio = new Row(14, 5, hall1CentralBio, SeatType.STANDARD);
        Row row6Hall1CentralBio = new Row(14, 6, hall1CentralBio, SeatType.STANDARD);
        Row row7Hall1CentralBio = new Row(14, 7, hall1CentralBio, SeatType.STANDARD);
        Row row8Hall1CentralBio = new Row(14, 8, hall1CentralBio, SeatType.STANDARD);
        Row row9Hall1CentralBio = new Row(14, 9, hall1CentralBio, SeatType.STANDARD);
        Row row10Hall1CentralBio = new Row(14, 10, hall1CentralBio, SeatType.STANDARD);
        Row row11Hall1CentralBio = new Row(14, 11, hall1CentralBio, SeatType.STANDARD);
        Row row12Hall1CentralBio = new Row(16, 12, hall1CentralBio, SeatType.COUCH);
        Row row13Hall1CentralBio = new Row(16, 13, hall1CentralBio, SeatType.COUCH);

        List<Row> rows = List.of(row1Hall1CentralBio, row2Hall1CentralBio, row3Hall1CentralBio, row4Hall1CentralBio,
                row5Hall1CentralBio, row6Hall1CentralBio, row7Hall1CentralBio, row8Hall1CentralBio, row9Hall1CentralBio,
                row10Hall1CentralBio, row11Hall1CentralBio, row12Hall1CentralBio, row13Hall1CentralBio);

        rowRepository.saveAll(rows);

        //CREATED SEATS FOR ALL ROWS IN HALL1 IN CENTRALBIO
        createSeats(row1Hall1CentralBio, "A", row1Hall1CentralBio.getAmountOfSeats());
        createSeats(row2Hall1CentralBio, "B", row2Hall1CentralBio.getAmountOfSeats());
        createSeats(row3Hall1CentralBio, "C", row3Hall1CentralBio.getAmountOfSeats());
        createSeats(row4Hall1CentralBio, "D", row4Hall1CentralBio.getAmountOfSeats());
        createSeats(row5Hall1CentralBio, "E", row5Hall1CentralBio.getAmountOfSeats());
        createSeats(row6Hall1CentralBio, "F", row6Hall1CentralBio.getAmountOfSeats());
        createSeats(row7Hall1CentralBio, "G", row7Hall1CentralBio.getAmountOfSeats());
        createSeats(row8Hall1CentralBio, "H", row8Hall1CentralBio.getAmountOfSeats());
        createSeats(row9Hall1CentralBio, "I", row9Hall1CentralBio.getAmountOfSeats());
        createSeats(row10Hall1CentralBio, "J", row10Hall1CentralBio.getAmountOfSeats());
        createSeats(row11Hall1CentralBio, "K", row11Hall1CentralBio.getAmountOfSeats());
        createSeats(row12Hall1CentralBio, "L", row12Hall1CentralBio.getAmountOfSeats());
        createSeats(row13Hall1CentralBio, "M", row13Hall1CentralBio.getAmountOfSeats());

        //User to make a reservation
        UserWithRoles userDaniel = new UserWithRoles("Daniel", passwordEncoder.encode("password"), "test@email.com");
        roleRepository.save(new Role("USER"));
        Role roleUser = roleRepository.findById("USER").orElseThrow(()-> new NoSuchElementException("Role 'user' not found"));
        userDaniel.addRole(roleUser);
        UserWithRolesRepository.save(userDaniel);

        //CREATED SHOWINGS FOR HALL1 IN CENTRALBIO
        Showing BarbieAt12march3 = new Showing(hall1CentralBio, LocalTime.of(12, 0), barbie, 100, LocalDate.of(2024, 3, 18));
        Showing InceptionAt15march3 = new Showing(hall1CentralBio, LocalTime.of(15, 0), inception, 100, LocalDate.of(2024, 3, 18));
        Showing BatmanAt18march3 = new Showing(hall1CentralBio, LocalTime.of(18, 0), batman, 100, LocalDate.of(2024, 3, 18));
        Showing BarbieAt12march4 = new Showing(hall1CentralBio, LocalTime.of(12, 0), barbie, 100, LocalDate.of(2024, 3, 19));
        Showing InceptionAt15march4 = new Showing(hall1CentralBio, LocalTime.of(15, 0), inception, 100, LocalDate.of(2024, 3, 19));
        Showing BatmanAt18march4 = new Showing(hall1CentralBio, LocalTime.of(18, 0), batman, 100, LocalDate.of(2024, 3, 19));
        Showing BarbieAt12march5 = new Showing(hall1CentralBio, LocalTime.of(12, 0), barbie, 100, LocalDate.of(2024, 3, 20));
        Showing InceptionAt15march5 = new Showing(hall1CentralBio, LocalTime.of(15, 0), inception, 100, LocalDate.of(2024, 3, 20));
        Showing BatmanAt18march5 = new Showing(hall1CentralBio, LocalTime.of(18, 0), batman, 100, LocalDate.of(2024, 3, 20));
        Showing ShawshankAt21march5 = new Showing(hall1CentralBio, LocalTime.of(21, 0), shawshank, 100, LocalDate.of(2024, 3, 20));
        Showing ShawshankAt21march6 = new Showing(hall1CentralBio, LocalTime.of(21, 0), shawshank, 100, LocalDate.of(2024, 3, 21));
        Showing ShawshankAt21march7 = new Showing(hall1CentralBio, LocalTime.of(21, 0), shawshank, 100, LocalDate.of(2024, 3, 22));
        Showing ShawshankAt21march8 = new Showing(hall1CentralBio, LocalTime.of(21, 0), shawshank, 100, LocalDate.of(2024, 3, 23));
        Showing ShawshankAt21march9 = new Showing(hall1CentralBio, LocalTime.of(21, 0), shawshank, 100, LocalDate.of(2024, 3, 24));
        Showing TheBigShortAt21march9 = new Showing(hall1CentralBio, LocalTime.of(21, 0), bigShort, 100, LocalDate.of(2024, 3, 24));
        Showing TheWolfOfWallStreetAt21march9 = new Showing(hall1CentralBio, LocalTime.of(21, 0), wolfOfWallStreet, 100, LocalDate.of(2024, 3, 24));
        Showing TheGodfatherAt21march9 = new Showing(hall1CentralBio, LocalTime.of(21, 0), godfather, 100, LocalDate.of(2024, 3, 24));
        Showing TheStarWarsAt21march9 = new Showing(hall1CentralBio, LocalTime.of(21, 0), starWars, 100, LocalDate.of(2024, 3, 24));
        Showing TheBigShortAt21march10 = new Showing(hall1CentralBio, LocalTime.of(21, 0), bigShort, 100, LocalDate.of(2024, 3, 19));
        Showing TheWolfOfWallStreetAt21march10 = new Showing(hall1CentralBio, LocalTime.of(21, 0), wolfOfWallStreet, 100, LocalDate.of(2024, 3, 19));
        Showing TheGodfatherAt21march10 = new Showing(hall1CentralBio, LocalTime.of(21, 0), godfather, 100, LocalDate.of(2024, 3, 19));
        Showing TheStarWarsAt21march10 = new Showing(hall1CentralBio, LocalTime.of(21, 0), starWars, 100, LocalDate.of(2024, 3, 19));

        showingRepository.saveAll(List.of(BarbieAt12march3, InceptionAt15march3, BatmanAt18march3, BarbieAt12march4, InceptionAt15march4, BatmanAt18march4,
                BarbieAt12march5, InceptionAt15march5, BatmanAt18march5, ShawshankAt21march5, ShawshankAt21march6, ShawshankAt21march7, ShawshankAt21march8,
                ShawshankAt21march9, TheBigShortAt21march9, TheWolfOfWallStreetAt21march9, TheGodfatherAt21march9, TheStarWarsAt21march9, TheBigShortAt21march10,
                TheWolfOfWallStreetAt21march10, TheGodfatherAt21march10, TheStarWarsAt21march10));

        //CREATED SEATS FOR RESERVATION
        List<Seat> seats = List.of(seatRepository.findBySeatNumber("1A"), seatRepository.findBySeatNumber("2A"), seatRepository.findBySeatNumber("3A"));
        List<Seat> seats2 = List.of(seatRepository.findBySeatNumber("1B"), seatRepository.findBySeatNumber("2B"), seatRepository.findBySeatNumber("3B"));
        List<Seat> seats3 = List.of(seatRepository.findBySeatNumber("1C"), seatRepository.findBySeatNumber("2C"), seatRepository.findBySeatNumber("3C"));

        //CREATED RESERVATION FOR SHOWING1 IN HALL1 IN CENTRALBIO
        Reservation reservation1 = new Reservation(seats, TheBigShortAt21march9, userDaniel);
        Reservation reservation2 = new Reservation(seats2, InceptionAt15march3, userDaniel);
        Reservation reservation3 = new Reservation(seats3, BatmanAt18march3, userDaniel);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
    }

    public void createCinema2() {
        //MOVIES TO USE IN THE CINEMA

        //CREATED EMPIREBIO
        Cinema empireBio = new Cinema("Empire Bio", "Nørrebro, Copenhagen");
        cinemaRepository.save(empireBio);

        //CREATED HALLS FOR EMPIREBIO
        Hall hall1EmpireBio = new Hall(empireBio);
        hallRepository.save(hall1EmpireBio);

        //CREATED ROWS FOR HALL1 IN EMPIREBIO
        Row row1Hall1EmpireBio = new Row(12, 1, hall1EmpireBio, SeatType.COWBOY);
        Row row2Hall1EmpireBio = new Row(12, 2, hall1EmpireBio, SeatType.COWBOY);
        Row row3Hall1EmpireBio = new Row(14, 3, hall1EmpireBio, SeatType.STANDARD);
        Row row4Hall1EmpireBio = new Row(14, 4, hall1EmpireBio, SeatType.STANDARD);
        Row row5Hall1EmpireBio = new Row(14, 5, hall1EmpireBio, SeatType.STANDARD);
        Row row6Hall1EmpireBio = new Row(14, 6, hall1EmpireBio, SeatType.STANDARD);
        Row row7Hall1EmpireBio = new Row(14, 7, hall1EmpireBio, SeatType.STANDARD);
        Row row8Hall1EmpireBio = new Row(14, 8, hall1EmpireBio, SeatType.STANDARD);
        Row row9Hall1EmpireBio = new Row(14, 9, hall1EmpireBio, SeatType.STANDARD);
        Row row10Hall1EmpireBio = new Row(14, 10, hall1EmpireBio, SeatType.STANDARD);
        Row row11Hall1EmpireBio = new Row(14, 11, hall1EmpireBio, SeatType.STANDARD);
        Row row12Hall1EmpireBio = new Row(16, 12, hall1EmpireBio, SeatType.COUCH);
        Row row13Hall1EmpireBio = new Row(16, 13, hall1EmpireBio, SeatType.COUCH);

        List<Row> rows = List.of(row1Hall1EmpireBio, row2Hall1EmpireBio, row3Hall1EmpireBio, row4Hall1EmpireBio,
                row5Hall1EmpireBio, row6Hall1EmpireBio, row7Hall1EmpireBio, row8Hall1EmpireBio, row9Hall1EmpireBio,
                row10Hall1EmpireBio, row11Hall1EmpireBio, row12Hall1EmpireBio, row13Hall1EmpireBio);

        rowRepository.saveAll(rows);

        //CREATED SEATS FOR ALL ROWS IN HALL1 IN EMPIREBIO
        createSeats(row1Hall1EmpireBio, "A", row1Hall1EmpireBio.getAmountOfSeats());
        createSeats(row2Hall1EmpireBio, "B", row2Hall1EmpireBio.getAmountOfSeats());
        createSeats(row3Hall1EmpireBio, "C", row3Hall1EmpireBio.getAmountOfSeats());
        createSeats(row4Hall1EmpireBio, "D", row4Hall1EmpireBio.getAmountOfSeats());
        createSeats(row5Hall1EmpireBio, "E", row5Hall1EmpireBio.getAmountOfSeats());
        createSeats(row6Hall1EmpireBio, "F", row6Hall1EmpireBio.getAmountOfSeats());
        createSeats(row7Hall1EmpireBio, "G", row7Hall1EmpireBio.getAmountOfSeats());
        createSeats(row8Hall1EmpireBio, "H", row8Hall1EmpireBio.getAmountOfSeats());
        createSeats(row9Hall1EmpireBio, "I", row9Hall1EmpireBio.getAmountOfSeats());
        createSeats(row10Hall1EmpireBio, "J", row10Hall1EmpireBio.getAmountOfSeats());
        createSeats(row11Hall1EmpireBio, "K", row11Hall1EmpireBio.getAmountOfSeats());
        createSeats(row12Hall1EmpireBio, "L", row12Hall1EmpireBio.getAmountOfSeats());
        createSeats(row13Hall1EmpireBio, "M", row13Hall1EmpireBio.getAmountOfSeats());

        //CREATED SHOWINGS FOR HALL1 IN EMPIREBIO
        Showing pulpfictionAt12March19 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 19));
        Showing matrixAt15March19 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 19));
        Showing lordOfTheRingsAt18March19 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 19));
        Showing pulpfictionAt12March20 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 20));
        Showing matrixAt15March20 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 20));
        Showing lordOfTheRingsAt18March20 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 20));
        Showing pulpfictionAt12March21 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 21));
        Showing matrixAt15March21 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 21));
        Showing lordOfTheRingsAt18March21 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 21));
        Showing pulpfictionAt12March22 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 22));
        Showing matrixAt15March22 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 22));
        Showing lordOfTheRingsAt18March22 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 22));
        Showing pulpfictionAt12March23 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 23));
        Showing matrixAt15March23 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 23));
        Showing lordOfTheRingsAt18March23 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 23));
        Showing pulpfictionAt12March24 = new Showing(hall1EmpireBio, LocalTime.of(12, 0), movieRepository.findByNameLike("Pulp Fiction"), 100, LocalDate.of(2024, 3, 24));
        Showing matrixAt15March24 = new Showing(hall1EmpireBio, LocalTime.of(15, 0), movieRepository.findByNameLike("The Matrix"), 100, LocalDate.of(2024, 3, 24));
        Showing lordOfTheRingsAt18March24 = new Showing(hall1EmpireBio, LocalTime.of(18, 0), movieRepository.findByNameLike("The Lord of the Rings"), 100, LocalDate.of(2024, 3, 24));

        showingRepository.saveAll(List.of(pulpfictionAt12March19, matrixAt15March19, lordOfTheRingsAt18March19, pulpfictionAt12March20, matrixAt15March20, lordOfTheRingsAt18March20,
                pulpfictionAt12March21, matrixAt15March21, lordOfTheRingsAt18March21, pulpfictionAt12March22, matrixAt15March22, lordOfTheRingsAt18March22,
                pulpfictionAt12March23, matrixAt15March23, lordOfTheRingsAt18March23, pulpfictionAt12March24, matrixAt15March24, lordOfTheRingsAt18March24));

    }
}
