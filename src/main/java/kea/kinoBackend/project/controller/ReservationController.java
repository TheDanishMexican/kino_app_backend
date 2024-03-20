package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ReservationDTO getReservation(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("/user")
    public List<ReservationDTO> getReservationsByUser(@RequestHeader("Authorization") String bearerToken) {
        // Remove the 'Bearer ' prefix from the token
        String token = bearerToken.substring(7);

        // Decode the token
        Jwt jwt = jwtDecoder.decode(token);
        System.out.println(jwt);

        // Extract the username claim
        String currentUsername = jwt.getSubject();; // adjust the claim name if necessary

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

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id) {
        ReservationDTO reservation = reservationService.getReservationById(id);

        // Get the currently authenticated user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername;
        if (principal instanceof UserDetails) {
            currentUsername = ((UserDetails)principal).getUsername();
        } else {
            currentUsername = principal.toString();
        }

        // Check if the currently authenticated user is the same as the user who made the reservation
        if (reservation.getUsername().equals(currentUsername)) {
            return reservationService.deleteReservation(id);
        } else {
            return new ResponseEntity<>("You are not authorized to delete this reservation", HttpStatus.UNAUTHORIZED);
        }
    }
}
