package library.application.service.会員;

import library.application.repository.会員リポジトリ;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.member.会員登録の状態;
import org.springframework.stereotype.Service;

@Service
public class 会員参照サービス {
    会員リポジトリ 会員リポジトリ;

    会員参照サービス(会員リポジトリ 会員リポジトリ) {
        this.会員リポジトリ = 会員リポジトリ;
    }

    public 会員 会員を見つける(会員番号 会員番号) {
        return 会員リポジトリ.findBy(会員番号);
    }

    public 会員登録の状態 会員登録の状態を確認する(会員番号 会員番号) {
        return 会員リポジトリ.status(会員番号);
    }
}
