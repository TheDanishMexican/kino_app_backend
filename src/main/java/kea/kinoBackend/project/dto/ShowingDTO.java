package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public record ShowingDTO(Integer id, int hallID, @JsonFormat(pattern = "HH:mm")LocalTime startTime, @JsonFormat(pattern = "HH:mm")LocalTime endTime,String filmTitle, int durationInMinutes, Set<DayOfWeek> weekdays) {}



