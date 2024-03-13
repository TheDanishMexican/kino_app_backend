package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.service.ShowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showings")
public class ShowingController {
    private ShowingService showingService;

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

    @PostMapping
    public ShowingDTO addShowing(@RequestBody ShowingDTO request) {
        return showingService.addShowing(request);
    }
}
