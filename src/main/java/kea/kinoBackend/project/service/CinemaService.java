package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.CinemaDTO;
import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.model.Cinema;
import kea.kinoBackend.project.repository.CinemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CinemaService {
    private CinemaRepository cinemaRepository;
    private HallService hallService;

    public CinemaService(CinemaRepository cinemaRepository, HallService hallService) {
        this.cinemaRepository = cinemaRepository;
        this.hallService = hallService;
    }

    public CinemaDTO getCinemaById(int id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema not found"));
        return toDTO(cinema);
    }

    public List<CinemaDTO> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaDTO> cinemaResponses = cinemas.stream().map(this::toDTO).toList();

        return cinemaResponses;
    }

    public CinemaDTO addCinema(CinemaDTO request) {
        if (request.id() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new cinema");
        }
        Cinema newCinema = new Cinema();
        updateCinema(newCinema, request);
        cinemaRepository.save(newCinema);
        return toDTO(newCinema);
    }

    public CinemaDTO editCinema(CinemaDTO request, int id) {
        if (request.id() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the id of an existing cinema");
        }
        Cinema cinemaToEdit = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema not found"));
        updateCinema(cinemaToEdit, request);
        cinemaRepository.save(cinemaToEdit);
        return toDTO(cinemaToEdit);
    }

    public void updateCinema(Cinema original, CinemaDTO request) {
        original.setName(request.name());
        original.setLocation(request.location());
    }


    private CinemaDTO toDTO(Cinema cinema) {

        List<HallDTO> hallDTOs = cinema.getHalls().stream().map(hallService::toDTO).toList();

        return new CinemaDTO(cinema.getId(), cinema.getName(), cinema.getLocation(), hallDTOs);
    }

    public ResponseEntity deleteCinema(int id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema not found"));
        cinemaRepository.delete(cinema);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
