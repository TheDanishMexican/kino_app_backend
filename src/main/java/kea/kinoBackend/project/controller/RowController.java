package kea.kinoBackend.project.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all rows", description = "Get a list of all rows")
    @GetMapping
    public List<RowDTO> getAllRows() {
        return rowService.getAllRows();
    }

    @Operation(summary = "Get one row", description = "Get a row by ID")
    @GetMapping("/{id}")
    public RowDTO getRowById(@PathVariable int id) {
        return rowService.getRowById(id);
    }

    @Operation(summary = "Add a new row", description = "Add a new row")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public RowDTO addRow(@RequestBody RowDTO request) {
        return rowService.addRow(request);
    }

    @Operation(summary = "Update a row", description = "Update a row")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public RowDTO updateRow(@RequestBody RowDTO request, @PathVariable int id) {
        return rowService.editRow(request, id);
    }

    @Operation(summary = "Delete a row", description = "Delete a row")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRow(@PathVariable int id) {
        return rowService.deleteRow(id);
    }
}
