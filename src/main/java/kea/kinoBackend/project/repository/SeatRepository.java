package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Optional<Seat> findByRowIdAndSeatNumber(int i, int i1);
}
