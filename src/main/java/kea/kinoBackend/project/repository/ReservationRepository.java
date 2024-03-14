package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findShowingsByHallID(int hallID);
}
