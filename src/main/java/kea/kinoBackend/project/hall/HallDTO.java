package kea.kinoBackend.project.hall;

import kea.kinoBackend.project.row.RowDTO;
import kea.kinoBackend.project.showing.ShowingDTO;

import java.util.List;

public record HallDTO(Integer id, int cinemaId, List<RowDTO> rows, List<ShowingDTO> showings) {}

