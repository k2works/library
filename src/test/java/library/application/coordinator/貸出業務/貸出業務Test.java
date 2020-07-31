package library.application.coordinator.貸出業務;

import library.LibraryDBTest;
import library.application.service.貸出.貸出参照サービス;
import library.application.service.会員.会員参照サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.rule.貸出可否;
import library.domain.model.loan.貸出依頼;
import library.domain.model.member.会員番号;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class 貸出業務Test {
    @Autowired
    貸出業務 貸出業務;

    @Autowired
    会員参照サービス 会員参照サービス;

    @Autowired
    貸出参照サービス 貸出参照サービス;

    @Test
    void 図書を貸し出すことができる() {
        貸出依頼 貸出依頼 =
                generate(1, "2-A", "2020-02-20");
        貸出可否 貸出可否 = 貸出業務.貸出制限を判断する(貸出依頼);

        assertSame(貸出可否, 貸出可否.貸出可能);
    }

    // FIXME 貸出可能になる
    //@Test
    void 貸出中の蔵書は貸し出すことができない() {
        貸出依頼 貸出依頼 =
                generate(2, "2-B", LoanDate.now().toString());
        貸出可否 貸出可否 = 貸出業務.貸出制限を判断する(貸出依頼);
        assertNotSame(貸出可否, 貸出可否.貸出可能);
    }

    @Test
    void 貸出制限冊数を超える会員には図書を貸し出すことができない() {
        List<String> list = List.of("2-C", "2-D", "2-E", "2-F", "2-G");
        for (String code : list) {
            貸出依頼 貸出依頼 =
                    generate(3, code, "2020-02-20");
            貸出業務.貸し出す(貸出依頼);
        }

        貸出依頼 貸出依頼 =
                generate(3, "2-H", "2020-02-20");

        貸出可否 貸出可否 = 貸出業務.貸出制限を判断する(貸出依頼);

        assertNotSame(貸出可否, 貸出可否.貸出可能);
    }

    private 貸出依頼 generate(int memberNumber, String itemNumber, String loanDate) {
        会員番号 member = new 会員番号(memberNumber);
        蔵書番号 item = new 蔵書番号(itemNumber);
        return new 貸出依頼(member, item, LoanDate.parse(loanDate));
    }
}