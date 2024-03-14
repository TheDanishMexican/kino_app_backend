package kea.kinoBackend.project.dto;

import java.time.LocalDateTime;

public record ShowingDTO(int id, int hallID, LocalDateTime timeAndDate, String filmTitle) {}

