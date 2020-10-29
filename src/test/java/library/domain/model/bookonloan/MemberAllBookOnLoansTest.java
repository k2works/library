package library.domain.model.bookonloan;

import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
import library.domain.model.member.Name;
import library.domain.type.date.Date;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberAllBookOnLoansTest {
    @ParameterizedTest
    @CsvSource({
        "大人, 2020-01-04, , 貸出５冊まで",
        "大人, 2020-01-04, 2020-01-03, 貸出不可",
        "大人, 2020-01-04, 2019-12-31, 貸出不可",
        "大人, 2020-01-04, 2019-12-30, 貸出不可",
        "子供, 2020-01-04, , 貸出７冊まで",
        "子供, 2020-01-04, 2020-01-03, 貸出４冊まで",
        "子供, 2020-01-04, 2019-12-31, 貸出４冊まで",
        "子供, 2020-01-04, 2019-12-30, 貸出不可"
    })
    void 貸出制限の判定ができる(MemberType memberType, String loanDate1, String loanDate2, LoanRestrictions expected) {
        Date today = Date.from("2020-01-20");
        Member member = new Member(new Name(""), memberType);
        List<BookOnLoan> bookOnLoans = new ArrayList<>();
        bookOnLoans.add(new BookOnLoan(null, new LoanDate(Date.from(loanDate1))));

        if (loanDate2 != null) {
            bookOnLoans.add(new BookOnLoan(null, new LoanDate(Date.from(loanDate2))));
        }

        MemberAllBookOnLoans memberAllBookOnLoans = new MemberAllBookOnLoans(member, bookOnLoans);

        assertEquals(expected, memberAllBookOnLoans.loanRestrictions(today));
    }
}
