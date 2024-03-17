package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Row;
import kea.kinoBackend.project.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatDTO> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{id}")
    public SeatDTO getSeat(@PathVariable int id) {
        return seatService.getSeatById(id);
    }

    @PostMapping
    public SeatDTO addSeat(@RequestBody SeatDTO request) {
        return seatService.addSeat(request);
    }

    @PutMapping("/{id}")
    public SeatDTO updateSeat(@RequestBody SeatDTO request, @PathVariable int id) {
        return seatService.editSeat(request, id);
    }

    @GetMapping("/{id}/row")
    public Row getRow(@PathVariable int id) {
        return seatService.getRowBySeatId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSeat(@PathVariable int id) {
        return seatService.deleteSeat(id);
    }

}
