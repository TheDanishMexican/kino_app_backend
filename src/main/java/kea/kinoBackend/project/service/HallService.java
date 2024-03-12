package kea.kinoBackend.project.service;

import kea.kinoBackend.project.model.Hall;
import kea.kinoBackend.project.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }
}
