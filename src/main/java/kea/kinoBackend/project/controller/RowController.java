package kea.kinoBackend.project.controller;

import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.service.RowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public RowDTO addRow(@RequestBody RowDTO request) {
        return rowService.addRow(request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public RowDTO updateRow(@RequestBody RowDTO request, @PathVariable int id) {
        return rowService.editRow(request, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRow(@PathVariable int id) {
        return rowService.deleteRow(id);
    }
}
