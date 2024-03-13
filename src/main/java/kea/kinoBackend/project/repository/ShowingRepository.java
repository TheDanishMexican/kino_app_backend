package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}
