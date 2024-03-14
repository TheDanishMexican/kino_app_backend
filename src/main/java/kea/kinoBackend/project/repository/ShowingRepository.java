package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}
