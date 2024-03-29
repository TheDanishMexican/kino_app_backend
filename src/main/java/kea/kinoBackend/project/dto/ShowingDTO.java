package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kea.kinoBackend.project.model.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ShowingDTO(Integer id, int hallId, @JsonFormat(pattern = "HH:mm")LocalTime startTime,
                         @JsonFormat(pattern = "HH:mm")LocalTime endTime, Movie movie, int durationInMinutes,
                         List<ReservationDTO> reservations, double price, int cinemaId, LocalDate showingDate, boolean specialMovie,
                         boolean is3dMovie) {}



