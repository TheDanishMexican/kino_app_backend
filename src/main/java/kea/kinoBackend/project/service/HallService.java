package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.model.Hall;
import kea.kinoBackend.project.repository.CinemaRepository;
import kea.kinoBackend.project.repository.HallRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {
    private HallRepository hallRepository;
    private RowService rowService;
    private ShowingService showingService;
    private CinemaRepository cinemaRepository;

    public HallService(HallRepository hallRepository, RowService rowService, ShowingService showingService, CinemaRepository cinemaRepository) {
        this.hallRepository = hallRepository;
        this.rowService = rowService;
        this.showingService = showingService;
        this.cinemaRepository = cinemaRepository;
    }
    /**
     * Get all halls
     * @return a list of all halls
     */
    public List<HallDTO> getAllHalls() {
        List<Hall> halls = hallRepository.findAll();
        List<HallDTO> hallResponses = halls.stream().map(this::toDTO).toList();

        return hallResponses;
    }

    /**
     * Get all halls in a cinema
     * @param id - the id of the cinema
     * @return a list of all halls in the cinema
     */
    public HallDTO getHallById(int id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hall not found"));
        return toDTO(hall);
    }

    /**
     * Adds a new hall
     * @param request the hall to add
     * @return the added hall
     */
    public HallDTO addHall(HallDTO request) {
        if (request.id() != null) {
            throw new IllegalArgumentException("You cannot provide the id for a new hall");
        }
        Hall newHall = new Hall();
        updateHall(newHall, request);
        hallRepository.save(newHall);
        return toDTO(newHall);
    }

    /**
     * Updates hall
     * @param original - the hall to update
     * @param request - the new hall data
     */
    public void updateHall(Hall original, HallDTO request) {
        System.out.println("hall added to cinema" + request.cinemaId());
        original.setCinema(cinemaRepository.findById(request.cinemaId()).orElseThrow(() -> new IllegalArgumentException("Cinema not found")));
    }

    public HallDTO editHall(HallDTO request, int id) {
        if (request.id() != id) {
            throw new IllegalArgumentException("You cannot change the id of an existing hall");
        }
        Hall hallToEdit = hallRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hall not found"));
        updateHall(hallToEdit, request);
        hallRepository.save(hallToEdit);
        return toDTO(hallToEdit);
    }

    /**
     * Deletes the hall with the given id
     * @param id - the id of the hall to delete
     * @return a response entity with the status code
     */
    public ResponseEntity deleteHall(int id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hall not found"));
        hallRepository.delete(hall);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    public HallDTO toDTO(Hall hall) {
        List<RowDTO> rowDTOs = hall.getRows().stream()
                .map(rowService::toDTO)
                .collect(Collectors.toList());

        List<ShowingDTO> showingDTOs = hall.getShowings().stream()
                .map(showingService::toDTO)
                .collect(Collectors.toList());

        return new HallDTO(
                hall.getId(), hall.getCinema().getId(), rowDTOs, showingDTOs);
    }


}
