package library.application.service.貸出;

import library.application.repository.貸出リポジトリ;
import library.domain.model.loan.Loan;
import library.domain.model.loan.rule.貸出状況;
import library.domain.model.item.蔵書番号;
import library.domain.model.member.会員番号;
import org.springframework.stereotype.Service;

@Service
public class 貸出参照サービス {
    貸出リポジトリ 貸出リポジトリ;

    貸出参照サービス(貸出リポジトリ 貸出リポジトリ) {
        this.貸出リポジトリ = 貸出リポジトリ;
    }

    public 貸出状況 会員の貸出を一覧する(会員番号 会員番号) {
        return 貸出リポジトリ.status(会員番号);
    }

    public Loan 貸出を見つける(蔵書番号 蔵書番号) {
        return 貸出リポジトリ.findBy(蔵書番号);
    }
}