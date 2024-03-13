package kea.kinoBackend.project.configuration;

import kea.kinoBackend.project.model.*;
import kea.kinoBackend.project.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Set;

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
         movieRepository.save(new Movie("The Matrix", LocalDate.of(1999, 5, 5), "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. It is the first installment in The Matrix film series, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano."));
         movieRepository.save(new Movie("The Matrix Reloaded", LocalDate.of(2003, 9, 20), "The Matrix Reloaded is a 2003 science fiction action film, the first sequel to The Matrix, and the second installment in The Matrix film series, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Revolutions", LocalDate.of(2003, 2, 17), "The Matrix Revolutions is a 2003 science fiction action film, the third installment of The Matrix trilogy, written and directed by the Wachowskis."));
         movieRepository.save(new Movie("The Matrix Resurrections", LocalDate.of(2021, 7, 11), "The Matrix Resurrections is a 2021 science fiction action film produced, co-written, and directed by Lana Wachowski. It is the fourth installment in The Matrix film series, set twenty years after the events of the third film, The Matrix Revolutions."));
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

        // Save the Showing instance using your repository
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
    }
}
