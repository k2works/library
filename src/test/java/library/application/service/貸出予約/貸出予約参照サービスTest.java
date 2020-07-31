package library.application.service.貸出予約;

import library.LibraryDBTest;
import library.application.service.本.本参照サービス;
import library.application.service.会員.会員参照サービス;
import library.domain.model.item.bibliography.キーワード;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.availability.BookAvailability;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.貸出予約一覧;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class 貸出予約参照サービスTest {
    @Autowired
    貸出予約参照サービス 貸出予約参照サービス;

    @Autowired
    貸出予約登録サービス 貸出予約登録サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Autowired
    本参照サービス 本参照サービス;

    @Test
    void 予約を一覧できる() {
        会員 会員 = 会員参照サービス.会員を見つける(new 会員番号(1));
        BookAvailability book = 本参照サービス.キーワードで本を探す(new キーワード("ハンドブック")).asList().get(0);
        貸出予約 tryingToReserveBook = 貸出予約.する(会員, book.book());
        貸出予約登録サービス.予約する(tryingToReserveBook);

        貸出予約一覧 貸出予約一覧 = 貸出予約参照サービス.未準備の予約を一覧する();

        assertAll(
                () -> assertEquals("1件", 貸出予約一覧.numberOfReservation().toString()));
    }
}