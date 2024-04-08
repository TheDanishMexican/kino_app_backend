package kea.kinoBackend.project.cinema;

import kea.kinoBackend.project.dto.HallIdDTO;

import java.util.List;

public record CinemaDTO(Integer id, String name, String location, List<HallIdDTO> halls) {
}
