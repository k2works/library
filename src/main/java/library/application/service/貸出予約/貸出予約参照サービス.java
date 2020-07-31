package library.application.service.貸出予約;

import library.application.repository.予約リポジトリ;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約一覧;
import org.springframework.stereotype.Service;

@Service
public class 貸出予約参照サービス {
    予約リポジトリ 予約リポジトリ;

    貸出予約参照サービス(予約リポジトリ 予約リポジトリ) {
        this.予約リポジトリ = 予約リポジトリ;
    }

    public 貸出予約一覧 未準備の予約を一覧する() {
        return 予約リポジトリ.貸出予約一覧();
    }

    public 貸出予約 予約を見つける(予約番号 予約番号) {
        return 予約リポジトリ.findBy(予約番号);
    }
}
