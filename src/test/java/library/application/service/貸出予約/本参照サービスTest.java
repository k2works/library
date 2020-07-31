package library.application.service.貸出予約;

import library.LibraryDBTest;
import library.application.service.本.本参照サービス;
import library.domain.model.item.bibliography.本;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.キーワード;
import library.domain.model.reservation.availability.本の一覧と貸出可否;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class 本参照サービスTest {
    @Autowired
    本参照サービス 本参照サービス;

    @Test
    void 本を検索できる() {
        キーワード キーワード = new キーワード("ハンドブック");
        本の一覧と貸出可否 books = 本参照サービス.キーワードで本を探す(キーワード);

        assertAll(
                () -> assertEquals(1, books.size()),
                () -> assertEquals(
                        "RDRA2.0 ハンドブック: 軽く柔軟で精度の高い要件定義のモデリング手法 (神崎善司)",
                        books.asList().get(0).describeBook()));
    }

    @Test
    void 本を取得できる() {
        本 本 = 本参照サービス.書籍番号で本を見つける(new 書籍番号(1));
        assertEquals(本.bookNumber().value(), 1);
    }
}