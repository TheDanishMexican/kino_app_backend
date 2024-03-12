package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.HallDTO;
import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.ShowingDTO;
import kea.kinoBackend.project.model.Hall;
import kea.kinoBackend.project.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {
    private HallRepository hallRepository;
    private RowService rowService;
    private ShowingService showingService;

    public HallService(HallRepository hallRepository, RowService rowService, ShowingService showingService) {
        this.hallRepository = hallRepository;
        this.rowService = rowService;
        this.showingService = showingService;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
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
