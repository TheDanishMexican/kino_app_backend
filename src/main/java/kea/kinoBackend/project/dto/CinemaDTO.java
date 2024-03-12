package kea.kinoBackend.project.dto;

import java.util.List;

public record CinemaDTO(int id, String name, String location, List<HallDTO> halls) {
}
