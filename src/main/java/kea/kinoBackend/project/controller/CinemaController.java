package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.CinemaDTO;
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

    @GetMapping("/{id}")
    public CinemaDTO getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }

    @GetMapping
    public List<CinemaDTO> getAllCinemas() {
        return cinemaService.getAllCinemas();
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
