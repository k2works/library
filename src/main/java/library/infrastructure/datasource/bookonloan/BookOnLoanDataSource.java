package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoan;
import org.springframework.stereotype.Repository;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    @Override
    public void registerBookOnLoan(BookOnLoan bookOnLoan) {

    }

    @Override
    public BookOnLoan findBookOnLoans() {
        return null;
    }
}
