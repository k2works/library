package library.application.service.returnbook;

import library.LibraryDBTest;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.holding.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.BookOnLoan;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnBookRecordServiceTest {
    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Autowired
    BookOnLoanRecordService bookOnLoanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 返却を登録できる() {
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByItemNumber(new ItemNumber("1-A"));

        ReturningBookOnLoan returningBookOnLoan = new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-20")));
        returnBookRecordService.registerReturnBook(returningBookOnLoan);

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByItemNumber(new ItemNumber("1-A"));
        });
    }
}