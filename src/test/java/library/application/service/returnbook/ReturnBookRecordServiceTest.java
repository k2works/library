package library.application.service.returnbook;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.ReturnDate;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.LoanDate;
import library.domain.model.bookonloan.LoaningOfBook;
import library.domain.model.bookonloan.ReturningBookOnLoan;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
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
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 返却を登録できる() {
        BookOnLoan bookOnLoan = bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("1-A"));

        ReturningBookOnLoan returningBookOnLoan = new ReturningBookOnLoan(bookOnLoan, new ReturnDate(Date.from("2020-02-20")));
        returnBookRecordService.registerReturnBook(returningBookOnLoan);

        assertThrows(NullPointerException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByBookCollectionCode(new BookCollectionCode("1-A"));
        });
    }

    private void registerBookOnLoan(BookCollectionCode bookCollectionCode) {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(bookCollectionCode);
        LoaningOfBook loaningOfBook = new LoaningOfBook(member, bookCollection, new LoanDate(Date.from("2020-02-20")));
        bookOnLoanRecordService.registerBookOnLoan(loaningOfBook);
    }
}