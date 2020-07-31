package library.application.coordinator.取置業務;

import library.LibraryDBTest;
import library.application.service.会員.会員参照サービス;
import library.application.service.本.本参照サービス;
import library.application.service.貸出予約.貸出予約参照サービス;
import library.application.service.貸出予約.貸出予約登録サービス;
import library.domain.model.item.bibliography.本;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.request.貸出予約;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@LibraryDBTest
class 取置業務Test {
    @Autowired
    取置業務 取置業務;

    @Autowired
    貸出予約参照サービス 貸出予約参照サービス;

    @Autowired
    貸出予約登録サービス 貸出予約登録サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Autowired
    本参照サービス 本参照サービス;

    @Test
    void 取置可能な貸出予約図書一覧を出力できる() {
        会員 会員 = 会員参照サービス.会員を見つける(new 会員番号(1));
        本 本 = 本参照サービス.書籍番号で本を見つける(new 書籍番号(2));
        貸出予約 予約 = 貸出予約.する(会員, 本);
        貸出予約登録サービス.予約する(予約);

        // TODO 仕様から再定義
//        Reservations reservations = retentionCoordinator.retention();
//        Reservation reservation1 = reservations.asList().get(0);
//
//        assertAll(
//                () ->assertTrue(reservation1.reservedBook().book().sameBook(book)),
//                () -> assertEquals(1, reservation1.member().memberNumber().value()));
    }
}