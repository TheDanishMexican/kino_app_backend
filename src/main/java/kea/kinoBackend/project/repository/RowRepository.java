package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Row;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowRepository extends JpaRepository<Row, Integer> {
}
