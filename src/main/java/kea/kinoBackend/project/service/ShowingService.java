package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
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

    public ShowingDTO toDTO(Showing showing) {
        return new ShowingDTO(
                showing.getId(),
                showing.getHall().getId(),
                showing.getTimeAndDate(),
                showing.getFilmTitle()
        );
    }
}
