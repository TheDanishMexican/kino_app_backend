package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kea.kinoBackend.project.model.Movie;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public record ShowingDTO(Integer id, int hallId, @JsonFormat(pattern = "HH:mm")LocalTime startTime, @JsonFormat(pattern = "HH:mm")LocalTime endTime, Movie movie, int durationInMinutes, Set<DayOfWeek> weekdays, List<ReservationDTO> reservations) {}



