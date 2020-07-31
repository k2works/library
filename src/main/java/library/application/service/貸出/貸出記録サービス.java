package library.application.service.貸出;

import library.application.repository.貸出リポジトリ;
import library.domain.model.loan.貸出依頼;
import org.springframework.stereotype.Service;

@Service
public class 貸出記録サービス {
    貸出リポジトリ 貸出リポジトリ;

    貸出記録サービス(貸出リポジトリ 貸出リポジトリ) {
        this.貸出リポジトリ = 貸出リポジトリ;
    }

    public void 貸出を記録する(貸出依頼 貸出依頼) {
        貸出リポジトリ.loan(貸出依頼);
    }
}