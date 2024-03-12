package kea.kinoBackend.project.dto;

import java.util.List;

public record HallDTO(int id, int cinemaID, List<RowDTO> rows, List<ShowingDTO> showings) {}

