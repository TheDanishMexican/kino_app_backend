package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findOverlappingShowings(int hallID, LocalDateTime timeAndDate, LocalDateTime endTime, int showingIdToIgnore);
}
