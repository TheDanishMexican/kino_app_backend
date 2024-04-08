package kea.kinoBackend.project.hall;

import kea.kinoBackend.project.hall.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    List<Hall> findAllByCinemaId(int id);

}
