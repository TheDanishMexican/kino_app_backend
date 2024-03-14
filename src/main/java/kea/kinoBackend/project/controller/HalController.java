package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
public class HalController {

    private final HallService hallService;

    public HalController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public List<HallDTO> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping("/{id}")
    public HallDTO getHallById(@PathVariable int id) {
        return hallService.getHallById(id);
    }

    @PostMapping
    public HallDTO addHall(@RequestBody HallDTO request) {
        return hallService.addHall(request);
    }

    @PutMapping("/{id}")
    public HallDTO updateHall(@RequestBody HallDTO request, @PathVariable int id) {
        return hallService.editHall(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHall(@PathVariable int id) {
        return hallService.deleteHall(id);
    }
}
