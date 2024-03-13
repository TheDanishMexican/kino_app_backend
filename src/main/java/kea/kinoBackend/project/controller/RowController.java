package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.service.RowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rows")
public class RowController {
    private RowService rowService;

    public RowController(RowService rowService) {
        this.rowService = rowService;
    }

    @GetMapping
    public List<RowDTO> getAllRows() {
        return rowService.getAllRows();
    }

    @GetMapping("/{id}")
    public RowDTO getRowById(@PathVariable int id) {
        return rowService.getRowById(id);
    }

    @PostMapping
    public RowDTO addRow(@RequestBody RowDTO request) {
        return rowService.addRow(request);
    }

    @PutMapping("/{id}")
    public RowDTO updateRow(@RequestBody RowDTO request, @PathVariable int id) {
        return rowService.editRow(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRow(@PathVariable int id) {
        rowService.deleteRow(id);
    }
}
