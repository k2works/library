package library.infrastructure.datasource.reservation;

import library.application.repository.予約リポジトリ;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.貸出予約一覧;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReservationDatasource implements 予約リポジトリ {
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ReservationDatasource(ReservationMapper reservationMapper, RetentionMapper retentionMapper) {
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
    }

    @Override
    @Transactional
    public void 予約する(貸出予約 貸出予約) {
        予約番号 予約番号 = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                予約番号,
                貸出予約.memberNumber(),
                貸出予約.bookNumber());

        retentionMapper.insert未準備(予約番号);
    }

    @Override
    public 貸出予約一覧 貸出予約一覧() {
        List<貸出予約> 貸出予約s = reservationMapper.selectAllReservation();
        return new 貸出予約一覧(貸出予約s);
    }

    @Override
    public 貸出予約 findBy(予約番号 予約番号) {
        return reservationMapper.selectReservation(予約番号);
    }

    @Override
    @Transactional
    public void 取消す(貸出予約 貸出予約) {
        予約番号 予約番号 = 貸出予約.number();
        reservationMapper.cancelReservation(予約番号);
        retentionMapper.delete未準備(予約番号);
    }
}
