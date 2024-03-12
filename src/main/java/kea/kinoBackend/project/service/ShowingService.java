package kea.kinoBackend.project.service;

import kea.kinoBackend.project.model.Showing;
import kea.kinoBackend.project.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {
    private ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }
}
