package kea.kinoBackend.project.dto;

import java.util.List;

public record HallDTO(Integer id, int theaterID, List<RowDTO> rows, List<ShowingDTO> showings) {}

