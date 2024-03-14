package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByGenresContaining(String genre);

    Movie findByNameLike(String name);
}
