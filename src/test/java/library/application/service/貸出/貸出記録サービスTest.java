package library.application.service.貸出;

import library.LibraryDBTest;
import library.application.service.会員.会員参照サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.member.会員番号;
import library.domain.model.loan.貸出依頼;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class 貸出記録サービスTest {
    @Autowired
    貸出記録サービス 貸出記録サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Autowired
    貸出参照サービス 貸出参照サービス;

    @Test
    void 貸出を登録できる() {
        蔵書番号 蔵書番号 = new 蔵書番号("2-A");
        貸出依頼 貸出依頼 = new 貸出依頼(new 会員番号(1), 蔵書番号, LoanDate.parse("2020-02-20"));
        貸出記録サービス.貸出を記録する(貸出依頼);

        Loan loan = 貸出参照サービス.貸出を見つける(蔵書番号);

        assertAll(
                () -> assertEquals(loan.member().number().value(), 1),
                () -> assertEquals(loan.date().toString(), "2020-02-20"));
    }
}