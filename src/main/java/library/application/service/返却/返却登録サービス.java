package library.application.service.返却;

import library.application.repository.貸出リポジトリ;
import library.domain.model.returned.返却;
import org.springframework.stereotype.Service;

@Service
public class 返却登録サービス {
    貸出リポジトリ 貸出リポジトリ;

    返却登録サービス(貸出リポジトリ 貸出リポジトリ) {
        this.貸出リポジトリ = 貸出リポジトリ;
    }

    public void 返却を記録する(返却 返却) {
        貸出リポジトリ.返却完了(返却);
    }
}
