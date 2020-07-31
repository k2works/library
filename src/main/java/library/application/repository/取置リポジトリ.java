package library.application.repository;

import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.retention.取置依頼;
import library.domain.model.reservation.retention.準備完了;
import library.domain.model.reservation.retention.準備完了の一覧;

/**
 * 取置リポジトリ
 */
public interface 取置リポジトリ {

    void retained(取置依頼 取置依頼);

    void released(準備完了 準備完了);
    void expired(準備完了 準備完了);


    準備完了の一覧 retentions();

    準備完了 findBy(蔵書番号 蔵書番号);
}
