package library.infrastructure.datasource.retention;

import library.application.repository.取置リポジトリ;
import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.retention.取置依頼;
import library.domain.model.reservation.retention.準備完了;
import library.domain.model.reservation.retention.RetainedDate;
import library.domain.model.reservation.retention.準備完了の一覧;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RetentionDatasource implements 取置リポジトリ {
    RetentionMapper retentionMapper;
    ItemMapper itemMapper;
    ReservationMapper reservationMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RetentionDatasource(RetentionMapper retentionMapper, ItemMapper itemMapper, ReservationMapper reservationMapper) {
        this.retentionMapper = retentionMapper;
        this.itemMapper = itemMapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public 準備完了 findBy(蔵書番号 蔵書番号) {
        return retentionMapper.select準備完了(蔵書番号);
    }

    @Override
    @Transactional
    public void retained(取置依頼 取置依頼) {
        予約番号 予約番号 = 取置依頼.reservationNumber();
        蔵書番号 蔵書番号 = 取置依頼.蔵書番号();
        RetainedDate retainedDate = RetainedDate.now();

        // 取置の発生の記録
        retentionMapper.insert取置履歴(予約番号, 蔵書番号, retainedDate);

        // 予約の状態
        retentionMapper.insert準備完了(予約番号, 蔵書番号, retainedDate);
        retentionMapper.delete未準備(予約番号);

        // 蔵書の状態
        itemMapper.delete貸出可能(蔵書番号);
        itemMapper.insert取置中(蔵書番号);
    }

    @Override
    @Transactional
    public void released(準備完了 準備完了) {
        蔵書番号 蔵書番号 = 準備完了.itemNumber();
        retentionMapper.insert取置解放履歴(準備完了.reservationNumber(), 蔵書番号);

        // 蔵書の状態
        itemMapper.delete取置中(蔵書番号);
        itemMapper.insert貸出可能(蔵書番号);

        //　予約の状態
        retentionMapper.delete準備完了(蔵書番号);

    }

    @Override
    @Transactional
    public void expired(準備完了 準備完了) {
        retentionMapper.insert取置期限切れ履歴(準備完了.reservationNumber());
    }

    @Override
    public 準備完了の一覧 retentions() {
        List<準備完了> list = retentionMapper.selectAll準備完了();
        return new 準備完了の一覧(list);
    }
}
