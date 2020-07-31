package library.application.service.貸出予約;

import library.LibraryDBTest;
import library.application.service.本.本参照サービス;
import library.application.service.会員.会員参照サービス;
import library.domain.model.item.bibliography.キーワード;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.availability.BookAvailability;
import library.domain.model.reservation.request.貸出予約;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@LibraryDBTest
class 貸出予約RecordServiceTest {
    @Autowired
    貸出予約登録サービス 貸出予約登録サービス;

    @Autowired
    貸出予約参照サービス 貸出予約参照サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Autowired
    本参照サービス 本参照サービス;

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    void 貸出予約を登録することができる() {
        会員 会員 = 会員参照サービス.会員を見つける(new 会員番号(1));
        BookAvailability book = 本参照サービス.キーワードで本を探す(new キーワード("ハンドブック")).asList().get(0);

        貸出予約 tryingToReserveBook = 貸出予約.する(会員, book.book());
        貸出予約登録サービス.予約する(tryingToReserveBook);

        List<貸出予約> result = reservationMapper.selectAllReservation();

        assertEquals(result.size(), 1);
    }

    @Test
    void 予約を取り消すことができる() {
        会員 会員 = 会員参照サービス.会員を見つける(new 会員番号(2));
        BookAvailability book = 本参照サービス.キーワードで本を探す(new キーワード("ハンドブック")).asList().get(0);

        貸出予約 tryingToReserveBook = 貸出予約.する(会員, book.book());
        貸出予約登録サービス.予約する(tryingToReserveBook);

        貸出予約 貸出予約 = reservationMapper.selectAllReservation().get(0);

        貸出予約登録サービス.予約を取消す(貸出予約);

        List<貸出予約> 貸出予約s = reservationMapper.selectAllReservation();

        assertTrue(貸出予約s.stream().noneMatch(r -> r.number().value() == 貸出予約.number().value()));
    }
}