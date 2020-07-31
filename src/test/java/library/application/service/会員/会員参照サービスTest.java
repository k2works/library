package library.application.service.会員;

import library.LibraryDBTest;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class 会員参照サービスTest {
    @Autowired
    会員参照サービス 会員参照サービス;

    @Test
    void 会員を取得できる() {
        会員番号 会員番号 = new 会員番号(1);
        会員 会員 = 会員参照サービス.会員を見つける(会員番号);

        assertEquals(会員.number().value(), 1);
    }
}