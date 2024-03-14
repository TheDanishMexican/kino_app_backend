package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.dto.RowDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Reservation;
import kea.kinoBackend.project.model.Row;
import kea.kinoBackend.project.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return toDto(reservation);
    }

    public ReservationDTO toDto(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getUserID(),
                reservation.getSeatID(),
                reservation.getShowing().getId()
        );
    }

//    public RowDTO toDTO(Row row) {
//        return new RowDTO(
//                row.getId(),
//                row.getAmountOfSeats(),
//                row.getRowNumber(),
//                row.getHall().getId(),
//                row.getSeatType(),
//                row.getSeats().stream()
//                        .map(seat -> new SeatDTO(seat.getId(), seat.getSeatNumber(), seat.isReserved(), seat.getRow().getId()))
//                        .collect(Collectors.toList())
//        );
//    }
}
