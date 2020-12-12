package library.infrastructure.datasource.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ReservationDatasource(ReservationMapper reservationMapper, RetentionMapper retentionMapper) {
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
    }

    @Override
    @Transactional
    public void registerReservation(Reservation reservation) {
        ReservationNumber reservationNumber = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                reservationNumber,
                reservation.memberNumber(),
                reservation.bookNumber());

        retentionMapper.insert取置依頼中(reservationNumber);
    }

    @Override
    public Reservations findReservations() {
        List<Reservation> reservations = reservationMapper.selectAllReservation();
        return new Reservations(reservations);
    }

    @Override
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationMapper.selectReservation(reservationNumber);
    }

    @Override
    @Transactional
    public void cancelReservation(Reservation reservation) {
        ReservationNumber reservationNumber = reservation.number();
        reservationMapper.cancelReservation(reservationNumber);
        retentionMapper.delete取置依頼中(reservationNumber);
    }
}
