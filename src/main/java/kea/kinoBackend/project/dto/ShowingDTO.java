package kea.kinoBackend.project.dto;

import java.time.LocalDateTime;

public record ShowingDTO(Integer id, int hallID, LocalDateTime timeAndDate, String filmTitle) {}

