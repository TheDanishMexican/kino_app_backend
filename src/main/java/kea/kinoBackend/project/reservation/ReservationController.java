package kea.kinoBackend.project.reservation;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;
    private JwtDecoder jwtDecoder;

    public ReservationController(ReservationService reservationService, JwtDecoder jwtDecoder) {
        this.reservationService = reservationService;
        this.jwtDecoder = jwtDecoder;
    }

    @Operation(summary = "Get all reservations", description = "Get a list of all reservations")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @Operation(summary = "Get one reservation", description = "Get a reservation by ID")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ReservationDTO getReservation(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @Operation(summary = "Add a new reservation", description = "Add a new reservation")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("/user")
    public List<ReservationDTO> getReservationsByUser(@RequestHeader("Authorization") String bearerToken) {
        // Remove the 'Bearer ' prefix from the token
        String token = bearerToken.substring(7);

        // Decode the token
        Jwt jwt = jwtDecoder.decode(token);
        System.out.println(jwt);

        // Extract the username from the token.
        String currentUsername = jwt.getSubject();;

        if (currentUsername == null) {
            throw new IllegalArgumentException("Username claim could not be found in the Jwt.");
        }

        System.out.println("Current user: " + currentUsername);

        // Fetch reservations for the currently authenticated user
        return reservationService.getReservationsByUser(currentUsername);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO request) {
        return reservationService.addReservation(request);
    }

    @Operation(summary = "delete a reservation", description = "delete a reservation")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id, @RequestHeader("Authorization") String bearerToken) {
        //Allow admin and staff to delete without checking token
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN") || a.getAuthority().equals("STAFF"))) {
            return reservationService.deleteReservation(id);
        }

        ReservationDTO reservation = reservationService.getReservationById(id);

        // Remove the 'Bearer ' prefix from the token
        String token = bearerToken.substring(7);

        // Decode the token
        Jwt jwt = jwtDecoder.decode(token);
        System.out.println(jwt);

        // Extract the username from the token.
        String currentUsername = jwt.getSubject();;

        if (currentUsername == null) {
            throw new IllegalArgumentException("Username claim could not be found in the Jwt.");
        }

        System.out.println("Current user: " + currentUsername);

        // Check if the currently authenticated user is the same as the user who made the reservation
        if (reservation.getUsername().equals(currentUsername)) {
            return reservationService.deleteReservation(id);
        } else {
            return new ResponseEntity<>("You are not authorized to delete this reservation", HttpStatus.UNAUTHORIZED);
        }
    }
}
