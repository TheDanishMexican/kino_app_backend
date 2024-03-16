package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservation(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO request) {
        return reservationService.addReservation(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable int id) {
        return reservationService.deleteReservation(id);
    }
}
