package library.application.service.返却;

import library.LibraryDBTest;
import library.application.service.貸出.貸出参照サービス;
import library.application.service.貸出.貸出記録サービス;
import library.application.service.会員.会員参照サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.返却;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class 返却登録サービスTest {
    @Autowired
    返却登録サービス 返却登録サービス;

    @Autowired
    貸出参照サービス 貸出参照サービス;

    @Autowired
    貸出記録サービス 貸出記録サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Test
    void 返却を登録できる() {
        蔵書番号 蔵書番号 = new 蔵書番号("1-A");
        ReturnDate returnDate = ReturnDate.parse("2020-02-20");

        返却 返却 = new 返却(蔵書番号, returnDate);
        返却登録サービス.返却を記録する(返却);

        assertThrows(IllegalArgumentException.class, () -> 貸出参照サービス.貸出を見つける(new 蔵書番号("1-A")));
    }
}