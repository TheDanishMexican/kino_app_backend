package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.CinemaDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    //Henter kun cinema med halls indeni som ID, ingen rows eller seats, de findes på hall get requestet
    @GetMapping("/{id}")
    public CinemaDTO getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }

    //Henter kun cinemas med halls indeni som ID, ingen rows eller seats, de findes på hall get requestet
    @GetMapping
    public List<CinemaDTO> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}/seats")
    public List<SeatDTO> getSeatsByCinemaId(@PathVariable int id) {
        return cinemaService.getSeatsByCinemaId(id);
    }

    @PostMapping
    public CinemaDTO addCinema(@RequestBody CinemaDTO request) {
        return cinemaService.addCinema(request);
    }

    @PutMapping("/{id}")
    public CinemaDTO updateCinema(@RequestBody CinemaDTO request, @PathVariable int id) {
        return cinemaService.editCinema(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCinema(@PathVariable int id) {
        return cinemaService.deleteCinema(id);
    }

}
