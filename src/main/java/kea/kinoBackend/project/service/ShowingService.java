package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.model.Showing;
import kea.kinoBackend.project.repository.HallRepository;
import kea.kinoBackend.project.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {
    private ShowingRepository showingRepository;
    private HallRepository hallRepository;

    public ShowingService(ShowingRepository showingRepository, HallRepository hallRepository) {
        this.showingRepository = showingRepository;
        this.hallRepository = hallRepository;
    }

    public List<ShowingDTO> findAllShowings() {
        List<Showing> showings = showingRepository.findAll();
        List<ShowingDTO> showingResponses = showings.stream().map(this::toDTO).toList();

        return showingResponses;
    }

    public ShowingDTO getShowingById(int id) {
        Showing showing = showingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Showing not found"));
        return toDTO(showing);
    }

    public ShowingDTO addShowing(ShowingDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new showing");
        }

        Showing newShowing = new Showing();
        updateShowing(newShowing, request);
        showingRepository.save(newShowing);
        return toDTO(newShowing);
    }

    public void updateShowing(Showing original, ShowingDTO request) {
        original.setTimeAndDate(request.timeAndDate());
        original.setFilmTitle(request.filmTitle());
        original.setHall(hallRepository.findById(request.hallID()).orElseThrow(() -> new IllegalArgumentException("Hall not found")));
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
