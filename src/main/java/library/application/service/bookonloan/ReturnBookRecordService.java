package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoan;

/**
 * 貸出図書の返却登録サービス
 */
public class ReturnBookRecordService {
    BookOnLoanRepository bookOnLoanRepository;

    ReturnBookRecordService(BookOnLoanRepository bookOnLoanRepository) {
        this.bookOnLoanRepository = bookOnLoanRepository;
    }

    public void registerReturnBook(BookOnLoan bookOnLoan) {
        bookOnLoanRepository.registerReturnBook(bookOnLoan);
    }
}
