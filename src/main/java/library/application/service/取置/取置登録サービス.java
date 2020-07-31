package library.application.service.取置;

import library.application.repository.取置き通知;
import library.application.repository.取置リポジトリ;
import library.domain.model.item.bibliography.本;
import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.progress.Progress;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.retention.BookMatching;
import library.domain.model.reservation.retention.取置依頼;
import library.domain.model.reservation.retention.準備完了;
import org.springframework.stereotype.Service;

import static library.domain.model.reservation.progress.Progress.未準備;
import static library.domain.model.reservation.progress.Progress.消込済;

@Service
public class 取置登録サービス {

    取置リポジトリ 取置リポジトリ;
    取置き通知 取置き通知;

    public 取置登録サービス(取置リポジトリ 取置リポジトリ, 取置き通知 取置き通知) {
        this.取置リポジトリ = 取置リポジトリ;
        this.取置き通知 = 取置き通知;
    }

    public BookMatching 予約された本であることを確認する(貸出予約 貸出予約, 蔵書 蔵書) {
        本 requested = 貸出予約.book();
        本 toRetain = 蔵書.book();
        return BookMatching.isSame(requested, toRetain);
    }

    public void 予約された本を取り置く(取置依頼 取置依頼) {

        進捗状態を確認する(未準備);
        取置リポジトリ.retained(取置依頼);

        準備完了 取置 = 取置リポジトリ.findBy(取置依頼.蔵書番号());
        取置き通知.retained(取置);
        進捗状態を確認する(Progress.準備完了);
    }

    /**
     * 取り置いた蔵書を貸し出す(準備完了を消しこむ)
     */
    public void 取り置いた蔵書を貸し出す(蔵書番号 蔵書番号) {
        進捗状態を確認する(Progress.準備完了);
        準備完了 取置 = 取置リポジトリ.findBy(蔵書番号);
        取置リポジトリ.released(取置);
        進捗状態を確認する(消込済);
    }

    /**
     * 取置を期限切れにする(準備完了を消し込む）
     */
    public void 取置を期限切れにする(蔵書番号 蔵書番号) {
        進捗状態を確認する(Progress.準備完了);
        準備完了 取置 = 取置リポジトリ.findBy(蔵書番号);
        取置リポジトリ.released(取置);
        取置リポジトリ.expired(取置);
        進捗状態を確認する(消込済);
    }

    /**
     * 事前/事後の進捗状態を確認する
     */
    private void 進捗状態を確認する(Progress progress) {
        // 進捗状態の表現の実験
        // TODO 状態の取得・判定・例外送出
        // 進捗という関心事の表現が目的
        // データの整合性のチェック目的ではない
    }

}
