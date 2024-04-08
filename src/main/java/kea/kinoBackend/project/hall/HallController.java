package kea.kinoBackend.project.hall;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @Operation(summary = "Get all halls", description = "Get a list of all halls")
    @GetMapping
    public List<HallDTO> getAllHalls() {
        return hallService.getAllHalls();
    }

    @Operation(summary = "Get one hall", description = "Get a hall by ID")
    @GetMapping("/{id}")
    public HallDTO getHallById(@PathVariable int id) {
        return hallService.getHallById(id);
    }

    @Operation(summary = "Add a new hall", description = "Add a new hall")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public HallDTO addHall(@RequestBody HallDTO request) {
        return hallService.addHall(request);
    }

    @Operation(summary = "Update a hall", description = "Update a hall")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public HallDTO updateHall(@RequestBody HallDTO request, @PathVariable int id) {
        return hallService.editHall(request, id);
    }

    @Operation(summary = "Delete a hall", description = "Delete a hall")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteHall(@PathVariable int id) {
        return hallService.deleteHall(id);
    }
}
