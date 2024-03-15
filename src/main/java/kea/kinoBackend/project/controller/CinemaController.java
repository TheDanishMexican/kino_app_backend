package kea.kinoBackend.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import kea.kinoBackend.project.dto.*;
import kea.kinoBackend.project.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Operation(summary = "Get one cinema", description = "Get a cinema by ID")
    //Henter kun cinema med halls indeni som ID, ingen rows eller seats, de findes på hall get requestet
    @GetMapping("/{id}")
    public CinemaDTO getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }

    @Operation(summary = "Get all cinemas", description = "Get a list of all cinemas")
    //Henter kun cinemas med halls indeni som ID, ingen rows eller seats, de findes på hall get requestet
    @GetMapping
    public List<CinemaDTO> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @Operation(summary = "Get all seats in a cinema", description = "Get a list of all seats in a cinema")
    @GetMapping("/{id}/seats")
    public List<SeatDTO> getAllSeatsInCinema(@PathVariable int id) {
        return cinemaService.getSeatsByCinemaId(id);
    }

    // refactor to optionals/body
    @GetMapping("/{cinemaId}/halls/{hallId}/seats")
    public ResponseEntity<List<SeatDTO>> getAllSeatsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<SeatDTO> seats = cinemaService.getAllSeatsInHall(cinemaId, hallId);
        if (seats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}/halls")
    public ResponseEntity<List<HallDTO>> getAllHallsInCinema(@PathVariable int id) {
        List<HallDTO> halls = cinemaService.getAllHallsByCinemaId(id);
        if (halls.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(halls);
    }

    // refactor to optionals/body
    @GetMapping("/{cinemaId}/halls/{hallId}/rows")
    public ResponseEntity<List<RowDTO>> getAllRowsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<RowDTO> rows = cinemaService.getAllRowsInHallInCinemaById(cinemaId, hallId);
        if (rows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rows);
    }

    @GetMapping("/{id}/showings")
    public ResponseEntity<List<ShowingDTO>> getAllShowingsInCinema(@PathVariable int id) {
        List<ShowingDTO> showings = cinemaService.getAllShowingsInCinemaById(id);
        if (showings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showings);
    }

    // refactor to optionals/body
    @GetMapping("/{cinemaId}/halls/{hallId}/showings")
    public ResponseEntity<List<ShowingDTO>> getAllShowingsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<ShowingDTO> showings = cinemaService.getAllShowingsInHallInCinemaById(cinemaId, hallId);
        if (showings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showings);
    }

    // refactor to optionals/body
    @GetMapping("/{cinemaId}/halls/{hallId}/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<ReservationDTO> reservations = cinemaService.getAllReservationsInHallInCinemaById(cinemaId, hallId);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }


    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsInCinema(@PathVariable int id) {
        List<ReservationDTO> reservations = cinemaService.getAllReservationsInCinemaById(id);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public CinemaDTO addCinema(@RequestBody CinemaDTO request) {
        return cinemaService.addCinema(request);
    }

    @PutMapping("/{id}")
    public CinemaDTO updateCinema(@RequestBody CinemaDTO request, @PathVariable int id) {
        return cinemaService.editCinema(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCinema(@PathVariable int id) {
        return cinemaService.deleteCinema(id);
    }

}
