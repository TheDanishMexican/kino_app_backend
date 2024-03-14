package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findAllByCinemaId(int id);
    List<Showing> findAllByCinemaIdAndHallId(int cinemaId, int hallId);
}
