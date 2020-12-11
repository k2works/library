package library.application.service.loan;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class LoanRegisterServiceTest {
    @Autowired
    LoanRegisterService loanRegisterService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Autowired
    LoanQueryService loanQueryService;

    @Test
    void 貸出図書を登録できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        ItemNumber itemNumber = new ItemNumber("2-A");
        Item itemInStock = itemQueryService.findItemInStock(itemNumber);
        LoanRequest loanRequest = new LoanRequest(member, itemInStock, LoanDate.parse("2020-02-20"));
        loanRegisterService.registerLoan(loanRequest);

        Loan loan = loanQueryService.findLoanByItemNumber(itemNumber);

        assertAll(
                () -> assertEquals(loan.member().number().value(), 1),
                () -> assertEquals(loan.date().toString(), "2020-02-20"));
    }
}