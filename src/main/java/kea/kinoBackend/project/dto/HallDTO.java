package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record HallDTO(@JsonIgnore Integer id, @JsonIgnore int cinemaId, List<RowDTO> rows, List<ShowingDTO> showings) {}

