package library.application.repository;

import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;

/**
 * 予約レポジトリ
 */
public interface ReservationRepository {
    void reserve(Reservation tryingToReserveBook);

    Reservations reservations();

    Reservation findBy(ReservationNumber reservationNumber);

    void cancel(Reservation reservation);
}