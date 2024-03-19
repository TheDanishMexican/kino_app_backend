package kea.kinoBackend.project.controller;

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
    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO request) {
        return reservationService.addReservation(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id) {
        return reservationService.deleteReservation(id);
    }
}
