package kea.kinoBackend.project.showing;

import io.swagger.v3.oas.annotations.Operation;
import kea.kinoBackend.project.row.RowDTO;
import kea.kinoBackend.project.seat.SeatDTO;
import kea.kinoBackend.project.reservation.PreReservationInfo;
import kea.kinoBackend.project.row.RowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/showings")
public class ShowingController {
    private ShowingService showingService;
    private RowService rowService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @Operation(summary = "Get all showings", description = "Get a list of all showings")
    @GetMapping
    public List<ShowingDTO> getAllShowings() {
        return showingService.findAllShowings();
    }

    @Operation(summary = "Get one showing", description = "Get a showing by ID")
    @GetMapping("/{id}")
    public ShowingDTO getShowing(@PathVariable int id) {
        return showingService.getShowingById(id);
    }

    @Operation(summary = "Add a new showing", description = "Add a new showing")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @PostMapping
    public ShowingDTO addShowing(@RequestBody ShowingDTO request) {
        return showingService.addShowing(request);
    }

    @Operation(summary = "Update a showing", description = "Update a showing")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @PutMapping("/{id}")
    public ShowingDTO updateShowing(@RequestBody ShowingDTO request, @PathVariable int id) {
        return showingService.editShowing(request, id);
    }


    @Operation(summary = "Get seat price in showing", description = "route is used to find the price of a specific seat in a specific showing it is for the frontend to be able to show the price of a specific seat inside a showing")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("/{id}/seat/{seatId}/price")
    public ResponseEntity<Map<String, Double>> getSeatInShowingPrice(@PathVariable int id, @PathVariable int seatId) {
        double seatPrice = showingService.getSeatPriceFromShowing(id, seatId);

        // Create a map to represent the response
        Map<String, Double> response = new HashMap<>();
        response.put("seatPrice", seatPrice);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a showing", description = "Delete a showing")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteShowing(@PathVariable int id) {
       return showingService.deleteShowing(id);
    }

    @Operation(summary="get all seats in a showing", description="get all seats in a showing")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("{id}/seats")
    public List<SeatDTO> getSeatsInShowing(@PathVariable int id) {
        return showingService.getSeatsInShowing(id);
    }

    @Operation(summary="get all reserved seats in a showing", description="get all reserved seats in a showing")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("{id}/reserved_seats")
    public List<SeatDTO> getReservedSeatsInShowing(@PathVariable int id) {
        return showingService.getReservedSeatsInShowing(id);
    }

    @Operation(summary="get all rows in a showing", description="get all rows in a showing")
    @GetMapping("{id}/rows")
    public List<RowDTO> getRowsInShowing(@PathVariable int id) {
        return showingService.getRowsInShowing(id);
    }

    @Operation(summary="get cinema id from showing", description="get cinema id from showing")
    @GetMapping("/{id}/cinema")
    public int getCinemaId(@PathVariable int id) {
        return showingService.getCinemaId(id);
    }

    @Operation(summary="calculate reservation price", description="calculate reservation price")
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @PostMapping("/reservation_price")
    public ResponseEntity<Map<String, Double>> calculateReservationPrice(
            @RequestBody PreReservationInfo preReservationInfo) {

        List<SeatDTO> selectedSeats = preReservationInfo.getSelectedSeats();
        int showingId = preReservationInfo.getShowingId();

        double price = showingService.reservationPrice(selectedSeats, showingId);

        Map<String, Double> response = new HashMap<>();
        response.put("reservationPrice", price);

        return ResponseEntity.ok().body(response);
    }

}
