package kea.kinoBackend.project.dto;

import java.util.List;

public record CinemaDTO(Integer id, String name, String location, List<HallIdDTO> halls) {
}
