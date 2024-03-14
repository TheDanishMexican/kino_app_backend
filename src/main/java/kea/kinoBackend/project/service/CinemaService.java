package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.*;
import kea.kinoBackend.project.model.Cinema;
import kea.kinoBackend.project.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CinemaService {
    private CinemaRepository cinemaRepository;
    private HallService hallService;
    private HallRepository hallRepository;
    private SeatRepository seatRepository;
    private SeatService seatService;
    private RowRepository rowRepository;
    private RowService rowService;
    private ShowingRepository showingRepository;
    private ShowingService showingService;


    public CinemaService(CinemaRepository cinemaRepository, HallService hallService, HallRepository hallRepository, SeatRepository seatRepository, SeatService seatService, RowRepository rowRepository, RowService rowService, ShowingRepository showingRepository, ShowingService showingService) {
        this.cinemaRepository = cinemaRepository;
        this.hallService = hallService;
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.seatService = seatService;
        this.rowRepository = rowRepository;
        this.rowService = rowService;
        this.showingRepository = showingRepository;
        this.showingService = showingService;
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

        List<HallIdDTO> hallIdDTOS = cinema.getHalls().stream()
                .map(hall -> new HallIdDTO(hall.getId())).toList();

        return new CinemaDTO(cinema.getId(), cinema.getName(), cinema.getLocation(), hallIdDTOS);
    }

    public ResponseEntity deleteCinema(int id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema not found"));
        cinemaRepository.delete(cinema);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public List<SeatDTO> getSeatsByCinemaId(int id) {
        return seatRepository.findAllByCinemaId(id).stream()
                .map(seatService::toDTO)
                .toList();
    }

    public List<SeatDTO> getAllSeatsInHall(int cinemaId, int hallId) {
        return seatRepository.findAllByCinemaIdAndHallId(cinemaId, hallId).stream()
                .map(seatService::toDTO)
                .toList();
    }

    public List<HallDTO> getAllHallsByCinemaId(int id) {
        return hallRepository.findAllByCinemaId(id).stream()
                .map(hallService::toDTO)
                .toList();
    }

    public List<RowDTO> getAllRowsInHallInCinemaById(int cinemaId, int hallId) {
        return rowRepository.findAllByCinemaIdAndHallId(cinemaId, hallId).stream()
                .map(rowService::toDTO)
                .toList();
    }

    public List<ShowingDTO> getAllShowingsInCinemaById(int id) {
        return showingRepository.findAllByCinemaId(id).stream()
                .map(showingService::toDTO)
                .toList();
    }

    public List<ShowingDTO> getAllShowingsInHallInCinemaById(int cinemaId, int hallId) {
        return showingRepository.findAllByCinemaIdAndHallId(cinemaId, hallId).stream()
                .map(showingService::toDTO)
                .toList();
    }



}
