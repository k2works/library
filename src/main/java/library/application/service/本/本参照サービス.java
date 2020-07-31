package library.application.service.本;

import library.domain.model.item.bibliography.キーワード;
import library.domain.model.item.bibliography.本;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.reservation.availability.本の一覧と貸出可否;
import org.springframework.stereotype.Service;

@Service
public class 本参照サービス {
    library.application.repository.本リポジトリ 本リポジトリ;

    本参照サービス(library.application.repository.本リポジトリ 本リポジトリ) {
        this.本リポジトリ = 本リポジトリ;
    }

    public 本 書籍番号で本を見つける(書籍番号 書籍番号) {
        return 本リポジトリ.見つける(書籍番号);
    }

    public 本の一覧と貸出可否 キーワードで本を探す(キーワード キーワード) {
        return 本リポジトリ.探す(キーワード);
    }
}
