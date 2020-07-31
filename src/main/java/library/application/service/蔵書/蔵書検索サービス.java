package library.application.service.蔵書;

import library.application.repository.蔵書リポジトリ;
import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書番号;
import library.domain.model.item.蔵書の状態;
import org.springframework.stereotype.Service;

@Service
public class 蔵書検索サービス {
    蔵書リポジトリ 蔵書リポジトリ;

    public 蔵書検索サービス(蔵書リポジトリ 蔵書リポジトリ) {
        this.蔵書リポジトリ = 蔵書リポジトリ;
    }

    public 蔵書の状態 蔵書の状態を調べる(蔵書番号 蔵書番号) {
        return 蔵書リポジトリ.status(蔵書番号);
    }
    public 蔵書 蔵書を見つける(蔵書番号 蔵書番号) {
        return 蔵書リポジトリ.findBy(蔵書番号);
    }
}
