package library.application.service.取置;

import library.application.repository.取置リポジトリ;
import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.retention.準備完了;
import library.domain.model.reservation.retention.準備完了の一覧;
import org.springframework.stereotype.Service;

@Service
public class 取置参照サービス {
    取置リポジトリ 取置リポジトリ;

    public 取置参照サービス(取置リポジトリ 取置リポジトリ) {
        this.取置リポジトリ = 取置リポジトリ;
    }

    public 準備完了の一覧 準備完了を一覧する() {
        return 取置リポジトリ.retentions();
    }

    public 準備完了 準備完了を見つける(蔵書番号 蔵書番号) {
        return 取置リポジトリ.findBy(蔵書番号);
    }
}
