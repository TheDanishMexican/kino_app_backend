package kea.kinoBackend.project.row;

import kea.kinoBackend.project.row.Row;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Integer> {
    List<Row> findAllByCinemaIdAndHallId(int cinemaId, int hallId);

    List<Row> findAllByHallId(int id);
}
