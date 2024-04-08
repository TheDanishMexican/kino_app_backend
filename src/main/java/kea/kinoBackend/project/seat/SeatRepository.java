package kea.kinoBackend.project.seat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Optional<Seat> findByRowIdAndSeatNumber(int i, String i1);
    List<Seat> findAllByCinemaId(int id);
    List<Seat> findAllByCinemaIdAndHallId(int cinemaId, int hallId);

    Seat findBySeatNumber(String s);
}
