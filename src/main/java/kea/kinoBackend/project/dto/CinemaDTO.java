package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record CinemaDTO(@JsonIgnore Integer id, String name, String location, List<HallIdDTO> halls) {
}
