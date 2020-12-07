package library.application.coordinator.retention;

import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.domain.model.counter.Retention;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.retention.RetentionableReservedBooks;
import org.springframework.stereotype.Service;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
    }

    /**
     * 取置可能な貸出予約図書一覧を出力する
     */
    public RetentionableReservedBooks retention() {
        Reservations reservations = reservationQueryService.findReservations();
        Retention retention = retentionQueryService.retention(reservations);
        return retention.retentionableReservedBooks(reservations);
    }
}
