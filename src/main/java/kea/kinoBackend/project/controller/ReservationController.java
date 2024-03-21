package kea.kinoBackend.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO request) {
        return reservationService.addReservation(request);
    }

    @Operation(summary = "delete a reservation", description = "delete a reservation")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id) {
        return reservationService.deleteReservation(id);
    }
}
