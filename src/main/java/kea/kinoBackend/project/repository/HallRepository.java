package kea.kinoBackend.project.repository;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    List<HallDTO> findAllByCinemaId(int id);
}
