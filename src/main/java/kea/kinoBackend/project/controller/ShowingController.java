package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.service.RowService;
import kea.kinoBackend.project.service.ShowingService;
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

    @GetMapping
    public List<ShowingDTO> getAllShowings() {
        return showingService.findAllShowings();
    }

    @GetMapping("/{id}")
    public ShowingDTO getShowing(@PathVariable int id) {
        return showingService.getShowingById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @PostMapping
    public ShowingDTO addShowing(@RequestBody ShowingDTO request) {
        return showingService.addShowing(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @PutMapping("/{id}")
    public ShowingDTO updateShowing(@RequestBody ShowingDTO request, @PathVariable int id) {
        return showingService.editShowing(request, id);
    }

    //The below route is used to find the price of a specific seat in a specific showing
    //it is for the frontend to be able to show the price of a specific seat inside a showing

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("/{id}/seat/{seatId}/price")
    public ResponseEntity<Map<String, Double>> getSeatInShowingPrice(@PathVariable int id, @PathVariable int seatId) {
        double seatPrice = showingService.getSeatPriceFromShowing(id, seatId);

        // Create a map to represent the response
        Map<String, Double> response = new HashMap<>();
        response.put("seatPrice", seatPrice);

        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteShowing(@PathVariable int id) {
       return showingService.deleteShowing(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("{id}/seats")
    public List<SeatDTO> getSeatsInShowing(@PathVariable int id) {
        return showingService.getSeatsInShowing(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF','USER')")
    @GetMapping("{id}/reserved_seats")
    public List<SeatDTO> getReservedSeatsInShowing(@PathVariable int id) {
        return showingService.getReservedSeatsInShowing(id);
    }
  
    @GetMapping("{id}/rows")
    public List<RowDTO> getRowsInShowing(@PathVariable int id) {
        return showingService.getRowsInShowing(id);
    }

    @GetMapping("/{id}/cinema")
    public int getCinemaId(@PathVariable int id) {
        return showingService.getCinemaId(id);
    }
}
