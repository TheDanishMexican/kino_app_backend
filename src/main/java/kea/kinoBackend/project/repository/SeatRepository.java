package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
