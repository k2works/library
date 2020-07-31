package library.application.coordinator.予約受付業務;

import library.application.service.会員.会員参照サービス;
import library.application.service.本.本参照サービス;
import library.application.service.貸出予約.貸出予約登録サービス;
import library.domain.model.item.bibliography.キーワード;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.本;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.member.会員登録の状態;
import library.domain.model.reservation.availability.本の一覧と貸出可否;
import library.domain.model.reservation.request.貸出予約;
import org.springframework.stereotype.Service;

@Service
public class 予約受付業務 {
    貸出予約登録サービス 貸出予約登録サービス;
    会員参照サービス 会員参照サービス;
    本参照サービス 本参照サービス;

    public 予約受付業務(貸出予約登録サービス 貸出予約登録サービス, 会員参照サービス 会員参照サービス, 本参照サービス 本参照サービス) {
        this.貸出予約登録サービス = 貸出予約登録サービス;
        this.会員参照サービス = 会員参照サービス;
        this.本参照サービス = 本参照サービス;
    }

    public 本の一覧と貸出可否 本を探す(キーワード キーワード) {
        return 本参照サービス.キーワードで本を探す(キーワード);
    }

    public 本 本を見つける(書籍番号 書籍番号) {
        return 本参照サービス.書籍番号で本を見つける(書籍番号);
    }

    public 会員登録の状態 会員番号の有効性を確認する(会員番号 会員番号) {
        return 会員参照サービス.会員登録の状態を確認する(会員番号);
    }

    public void 予約を記録する(本 本, 会員番号 会員番号) {
        会員 会員 = 会員参照サービス.会員を見つける(会員番号);
        貸出予約 予約 = 貸出予約.する(会員, 本);
        貸出予約登録サービス.予約する(予約);
    }
}
