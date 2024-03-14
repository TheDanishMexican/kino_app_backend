package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
