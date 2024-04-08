package kea.kinoBackend.project.seat;

import io.swagger.v3.oas.annotations.Operation;
import kea.kinoBackend.project.row.Row;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @Operation(summary = "Get all seats", description = "Get a list of all seats")
    @GetMapping
    public List<SeatDTO> getAllSeats() {
        return seatService.getAllSeats();
    }

    @Operation(summary = "Get one seat", description = "Get a seat by ID")
    @GetMapping("/{id}")
    public SeatDTO getSeat(@PathVariable int id) {
        return seatService.getSeatById(id);
    }

    @Operation(summary = "Add a new seat", description = "Add a new seat")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SeatDTO addSeat(@RequestBody SeatDTO request) {
        return seatService.addSeat(request);
    }

    @Operation(summary = "Update a seat", description = "Update a seat")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SeatDTO updateSeat(@RequestBody SeatDTO request, @PathVariable int id) {
        return seatService.editSeat(request, id);
    }

    @Operation(summary = "Get row by seat id", description = "Get a row by seat id")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'STAFF')")
    @GetMapping("/{id}/row")
    public Row getRow(@PathVariable int id) {
        return seatService.getRowBySeatId(id);
    }

    @Operation(summary = "Delete a seat", description = "Delete a seat")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSeat(@PathVariable int id) {
        return seatService.deleteSeat(id);
    }

}
