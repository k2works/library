package library.application.coordinator.returnbook;

import library.application.service.bookonloan.LoanQueryService;
import library.application.service.holding.ItemQueryService;
import library.application.service.returnbook.ReturnBookRecordService;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.loan.loan.ReturningBookOnLoan;
import org.springframework.stereotype.Service;

/**
 * 返却コーディネーター
 */
@Service
public class ReturnBookCoordinator {
    ReturnBookRecordService returnBookRecordService;
    LoanQueryService loanQueryService;
    ItemQueryService itemQueryService;

    public ReturnBookCoordinator(ReturnBookRecordService returnBookRecordService, LoanQueryService loanQueryService, ItemQueryService itemQueryService) {
        this.returnBookRecordService = returnBookRecordService;
        this.loanQueryService = loanQueryService;
        this.itemQueryService = itemQueryService;
    }

    /**
     * 貸出図書の返却を登録する
     */
    public void returnBook(ItemNumber itemNumber, ReturnDate returnDate) {
        itemQueryService.findHoldingOnLoan(itemNumber);

        Loan loan = loanQueryService.findBookOnLoanByItemNumber(itemNumber);
        returnBookRecordService.registerReturnBook(new ReturningBookOnLoan(loan, returnDate));
    }
}
