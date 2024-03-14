package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.model.Reservation;
import kea.kinoBackend.project.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return toDTO(reservation);
    }

    public ReservationDTO toDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getUserID(),
                reservation.getSeatID(),
                reservation.getShowing().getId(),
                reservation.getHallID()
        );
    }


}
