package library.application.service.貸出;

import library.LibraryDBTest;
import library.application.service.会員.会員参照サービス;
import library.application.service.返却.返却登録サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.貸出依頼;
import library.domain.model.member.会員番号;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.返却;
import library.domain.model.loan.rule.貸出状況;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class 貸出参照サービスTest {
    @Autowired
    貸出参照サービス 貸出参照サービス;

    @Autowired
    返却登録サービス 返却登録サービス;

    @Autowired
    貸出記録サービス 貸出記録サービス;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Test
    void 貸出を取得できる() {
        registerLoan(new 蔵書番号("2-A"), 1);

        Loan loan = 貸出参照サービス.貸出を見つける(new 蔵書番号("2-A"));

        assertEquals(loan.member().number().value(), 1);
    }

    @Test
    void 返却された貸出は取得できない() {
        蔵書番号 蔵書番号 = new 蔵書番号("2-B");
        registerLoan(蔵書番号, 1);
        返却登録サービス.返却を記録する(new 返却(蔵書番号, ReturnDate.parse("2020-02-21")));

        assertThrows(IllegalArgumentException.class, () -> 貸出参照サービス.貸出を見つける(蔵書番号));
    }

    @Test
    void 会員が現在借りている全貸出取得時に返却した貸出が含まれない() {
        蔵書番号 蔵書番号 = new 蔵書番号("2-B");
        registerLoan(蔵書番号, 2);
        返却登録サービス.返却を記録する(new 返却(蔵書番号, ReturnDate.parse("2020-02-21")));
        貸出状況 貸出状況 = 貸出参照サービス.会員の貸出を一覧する(new 会員番号(2));

        assertEquals(貸出状況.count(), 0);
    }

    private void registerLoan(蔵書番号 蔵書番号, int memberNumber) {
        会員番号 member = new 会員番号(memberNumber);
        貸出依頼 貸出依頼 = new 貸出依頼(member, 蔵書番号, LoanDate.parse("2020-02-20"));
        貸出記録サービス.貸出を記録する(貸出依頼);
    }
}