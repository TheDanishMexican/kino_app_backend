package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.model.Showing;
import kea.kinoBackend.project.repository.HallRepository;
import kea.kinoBackend.project.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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

        // Check if the time slot is available
        if (!isTimeSlotAvailable(request.hallID(), request.timeAndDate(), request.movieDuration(), null)) {
            throw new IllegalArgumentException("The time slot is not available for the showing");
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
        original.setMovieDuration(request.movieDuration());
    }

    public ShowingDTO editShowing(ShowingDTO request, int id) {
        Showing showing = showingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Showing not found"));

        // Check if the time slot is available
        if (!isTimeSlotAvailable(request.hallID(), request.timeAndDate(), request.movieDuration(), id)) {
            throw new IllegalArgumentException("Another showing already occupies the specified time slot");
        }

        updateShowing(showing, request);
        showingRepository.save(showing);
        return toDTO(showing);

    }

    private boolean isTimeSlotAvailable(int hallID, LocalDateTime timeAndDate, Duration duration, int showingIdToIgnore) {
        // Calculate end time of the showing
        LocalDateTime endTime = timeAndDate.plus(duration);

        // Query the database for showings in the same hall that overlap with the specified time span
        List<Showing> overlappingShowings = showingRepository.findOverlappingShowings(hallID, timeAndDate, endTime, showingIdToIgnore);

        // If there are any overlapping showings, the time slot is not available
        return overlappingShowings.isEmpty();
    }

    public ShowingDTO toDTO(Showing showing) {

        return new ShowingDTO(
                showing.getId(),
                showing.getHall().getId(),
                showing.getTimeAndDate(),
                showing.getFilmTitle(),
                showing.getMovieDuration()
        );
    }
}
