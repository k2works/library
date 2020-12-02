package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.Reservation;

/**
 * 貸出予約参照サービス
 */
public class ReservationQueryService {
    ReservationRepository reservationRepository;

    ReservationQueryService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /***
     * 在庫ありの貸出予約一覧
     */
    public Reservation findInStackReservations() {
        return reservationRepository.findInStockReservations();
    }
}
