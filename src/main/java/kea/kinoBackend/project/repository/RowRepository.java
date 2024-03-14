package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Row;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Integer> {
    List<Row> findAllByCinemaIdAndHallId(int cinemaId, int hallId);
}
