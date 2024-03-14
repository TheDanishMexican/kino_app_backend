package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
