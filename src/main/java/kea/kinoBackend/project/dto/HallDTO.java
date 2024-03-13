package kea.kinoBackend.project.dto;

import java.util.List;

public record HallDTO(Integer id, int hallID, List<RowDTO> rows, List<ShowingDTO> showings) {}

