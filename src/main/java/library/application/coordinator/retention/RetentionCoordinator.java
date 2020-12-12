package library.application.coordinator.retention;

import library.application.service.reservation.ReservationQueryService;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService) {
        this.reservationQueryService = reservationQueryService;
    }

    /**
     * 予約(取置依頼)を一覧する
     */
    public Reservations retentions() {
        List<Reservation> reservations = Collections.emptyList();
        return reservationQueryService.findReservations();
    }
}
