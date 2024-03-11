package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
