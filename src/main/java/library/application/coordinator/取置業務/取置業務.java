package library.application.coordinator.取置業務;

import library.application.service.取置.取置参照サービス;
import library.application.service.取置.取置登録サービス;
import library.application.service.蔵書.蔵書検索サービス;
import library.application.service.貸出.貸出記録サービス;
import library.application.service.貸出予約.貸出予約参照サービス;
import library.application.service.貸出予約.貸出予約登録サービス;
import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書の状態;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.貸出依頼;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.貸出予約一覧;
import library.domain.model.reservation.retention.BookMatching;
import library.domain.model.reservation.retention.取置依頼;
import library.domain.model.reservation.retention.準備完了;
import library.domain.model.reservation.retention.準備完了の一覧;
import org.springframework.stereotype.Service;

/**
 * 取置業務
 */
@Service
public class 取置業務 {
    貸出予約参照サービス 貸出予約参照サービス;
    貸出予約登録サービス 貸出予約登録サービス;
    取置参照サービス 取置参照サービス;
    取置登録サービス 取置登録サービス;
    蔵書検索サービス 蔵書検索サービス;
    貸出記録サービス 貸出記録サービス;

    public 取置業務(貸出予約参照サービス 貸出予約参照サービス, 貸出予約登録サービス 貸出予約登録サービス, 取置参照サービス 取置参照サービス, 取置登録サービス 取置登録サービス, 蔵書検索サービス 蔵書検索サービス, 貸出記録サービス 貸出記録サービス) {
        this.貸出予約参照サービス = 貸出予約参照サービス;
        this.貸出予約登録サービス = 貸出予約登録サービス;
        this.取置参照サービス = 取置参照サービス;
        this.取置登録サービス = 取置登録サービス;
        this.蔵書検索サービス = 蔵書検索サービス;
        this.貸出記録サービス = 貸出記録サービス;
    }

    public 貸出予約一覧 未準備の予約を一覧する() {
        return 貸出予約参照サービス.未準備の予約を一覧する();
    }

    public 貸出予約 予約を見つける(予約番号 予約番号) {
        return 貸出予約参照サービス.予約を見つける(予約番号);
    }

    public BookMatching 予約された本であることを確認する(貸出予約 貸出予約, 取置依頼 取置依頼) {
        蔵書 蔵書 = 蔵書検索サービス.蔵書を見つける(取置依頼.蔵書番号());
        return 取置登録サービス.予約された本であることを確認する(貸出予約, 蔵書);
    }

    public 蔵書の状態 蔵書の状態を確認する(蔵書番号 蔵書番号) {
        return 蔵書検索サービス.蔵書の状態を調べる(蔵書番号);
    }

    public void 取り置く(取置依頼 取置依頼) {
        取置登録サービス.予約された本を取り置く(取置依頼);
    }

    public 準備完了の一覧 準備完了を一覧する() {
        return 取置参照サービス.準備完了を一覧する();
    }

    public void 貸し出す(蔵書番号 蔵書番号) {
        // 貸出の実行
        準備完了 準備完了 = 取置参照サービス.準備完了を見つける(蔵書番号);
        貸出依頼 貸出依頼 = 準備完了.貸出依頼を作る();
        貸出記録サービス.貸出を記録する(貸出依頼);

        取置登録サービス.取り置いた蔵書を貸し出す(蔵書番号);
    }

    public void 取置の期限切れ(蔵書番号 蔵書番号) {
        取置登録サービス.取置を期限切れにする(蔵書番号);
    }

    public void 予約の取り消し(予約番号 予約番号) {
        貸出予約 貸出予約 = 貸出予約参照サービス.予約を見つける(予約番号);
        貸出予約登録サービス.予約を取消す(貸出予約);
    }
}
