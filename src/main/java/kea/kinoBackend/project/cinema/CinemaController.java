package kea.kinoBackend.project.cinema;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kea.kinoBackend.project.hall.HallDTO;
import kea.kinoBackend.project.reservation.ReservationDTO;
import kea.kinoBackend.project.row.RowDTO;
import kea.kinoBackend.project.seat.SeatDTO;
import kea.kinoBackend.project.showing.ShowingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info=@Info(
                title="Cinema API",
                version = "1.0",
                description = "API for cinemas, halls, rows, seats, showings and reservations"

    )
)
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Operation(summary = "Get one cinema", description = "Get a cinema by ID", responses = {@ApiResponse(responseCode = "200", content=@Content(mediaType = "application/json", schema = @Schema(implementation = Cinema.class)) ,description = "Cinema found"), @ApiResponse(responseCode= "404", content= @Content, description = "Cinema not found")})
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

    @Operation(summary = "Get all seats in hall in a cinema", description = "Get a list of all seats in halls in a cinema")
    @GetMapping("/{cinemaId}/halls/{hallId}/seats")
    public ResponseEntity<List<SeatDTO>> getAllSeatsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<SeatDTO> seats = cinemaService.getAllSeatsInHall(cinemaId, hallId);
        if (seats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(seats);
    }

    @Operation(summary = "Get all halls in a cinema", description = "Get a list of all halls in a cinema")
    @GetMapping("/{id}/halls")
    public ResponseEntity<List<HallDTO>> getAllHallsInCinema(@PathVariable int id) {
        List<HallDTO> halls = cinemaService.getAllHallsByCinemaId(id);
        if (halls.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(halls);
    }

    @Operation(summary = "Get all rows in hall in a cinema", description = "Get a list of all rows in halls in a cinema")
    @GetMapping("/{cinemaId}/halls/{hallId}/rows")
    public ResponseEntity<List<RowDTO>> getAllRowsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<RowDTO> rows = cinemaService.getAllRowsInHallInCinemaById(cinemaId, hallId);
        if (rows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rows);
    }

    @Operation(summary = "Get all showings in a cinema", description = "Get a list of all showings in a cinema")
    @GetMapping("/{id}/showings")
    public ResponseEntity<List<ShowingDTO>> getAllShowingsInCinema(@PathVariable int id) {
        List<ShowingDTO> showings = cinemaService.getAllShowingsInCinemaById(id);
        if (showings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showings);
    }

    @Operation(summary = "Get all showings in hall in a cinema", description = "Get a list of all showings in halls in a cinema")
    @GetMapping("/{cinemaId}/halls/{hallId}/showings")
    public ResponseEntity<List<ShowingDTO>> getAllShowingsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<ShowingDTO> showings = cinemaService.getAllShowingsInHallInCinemaById(cinemaId, hallId);
        if (showings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showings);
    }

    @Operation(summary = "Get all reservations in hall in a cinema", description = "Get a list of all reservations in halls in a cinema")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{cinemaId}/halls/{hallId}/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsInHallInCinema(@PathVariable("cinemaId") int cinemaId, @PathVariable("hallId") int hallId) {
        List<ReservationDTO> reservations = cinemaService.getAllReservationsInHallInCinemaById(cinemaId, hallId);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }


    @Operation(summary = "Get all reservations in a cinema", description = "Get a list of all reservations in a cinema")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsInCinema(@PathVariable int id) {
        List<ReservationDTO> reservations = cinemaService.getAllReservationsInCinemaById(id);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Add a cinema", description = "Add a new cinema")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CinemaDTO addCinema(@RequestBody CinemaDTO request) {
        return cinemaService.addCinema(request);
    }

    @Operation(summary = "Update a cinema", description = "Update a cinema")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CinemaDTO updateCinema(@RequestBody CinemaDTO request, @PathVariable int id) {
        return cinemaService.editCinema(request, id);
    }

    @Operation(summary = "Delete a cinema", description = "Delete a cinema")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCinema(@PathVariable int id) {
        return cinemaService.deleteCinema(id);
    }

}
