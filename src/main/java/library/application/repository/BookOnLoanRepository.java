package library.application.repository;

import library.domain.model.bookonloan.BookOnLoan;

/**
 * 貸出図書リポジトリ
 */
public interface BookOnLoanRepository {

    void registerBookOnLoan(BookOnLoan bookOnLoan);

    BookOnLoan findBookOnLoans();
}
