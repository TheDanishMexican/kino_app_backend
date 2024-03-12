package kea.kinoBackend.project.service;

import kea.kinoBackend.project.model.Row;
import kea.kinoBackend.project.repository.RowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowService {
    private RowRepository rowRepository;

    public RowService(RowRepository rowRepository) {
        this.rowRepository = rowRepository;
    }

    public List<Row> findAll() {
        return rowRepository.findAll();
    }
}
