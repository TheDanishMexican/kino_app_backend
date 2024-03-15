package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.ReservationDTO;
import kea.kinoBackend.project.dto.SeatDTO;
import kea.kinoBackend.project.model.Reservation;
import kea.kinoBackend.project.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private SeatService seatService;

    public ReservationService(ReservationRepository reservationRepository, SeatService seatService) {
        this.reservationRepository = reservationRepository;
        this.seatService = seatService;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationResponses = reservations.stream().map(this::toDTO).toList();

        return reservationResponses;
    }

    public ReservationDTO getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        return toDTO(reservation);
    }

    public ReservationDTO toDTO(Reservation reservation) {
        List<SeatDTO> seatDTOs = reservation.getSeats().stream().map(seatService::toDTO).toList();

        return new ReservationDTO(
                reservation.getId(),
                reservation.getUserId(),
                seatDTOs,
                reservation.getShowing().getId(),
                reservation.getHallId(),
                reservation.getTotalPrice(),
                reservation.getSeatPrice(),
                reservation.getUser().getUsername()
        );
    }


}
