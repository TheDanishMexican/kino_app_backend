package kea.kinoBackend.project.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findShowingsByHallId(int hallId);

    List<Reservation> findAllByCinemaIdAndHallId(int cinemaId, int hallId);

    List<Reservation> findAllByCinemaId(int id);

    List<Reservation> findAllByUser_Username(String user_username);
}

