package library.application.service.貸出予約;

import library.application.repository.予約リポジトリ;
import library.application.repository.取置き通知;
import library.domain.model.reservation.request.貸出予約;
import org.springframework.stereotype.Service;

@Service
public class 貸出予約登録サービス {
    予約リポジトリ 予約リポジトリ;
    取置き通知 取置き通知;

    public 貸出予約登録サービス(予約リポジトリ 予約リポジトリ, 取置き通知 取置き通知) {
        this.予約リポジトリ = 予約リポジトリ;
        this.取置き通知 = 取置き通知;
    }

    public void 予約する(貸出予約 tryingToReserveBook) {
        予約リポジトリ.予約する(tryingToReserveBook);
    }

    public void 予約を取消す(貸出予約 貸出予約) {
        予約リポジトリ.取消す(貸出予約);
        取置き通知.notAvailable(貸出予約);
    }
}
