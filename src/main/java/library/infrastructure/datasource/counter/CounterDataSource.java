package library.infrastructure.datasource.counter;

import library.application.repository.CounterRepository;
import library.domain.model.book.BookIds;
import library.domain.model.bookonloan.librarycard.*;
import library.domain.model.counter.Counter;
import library.domain.model.holding.Catalog;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingCode;
import library.domain.model.retention.RetentionShelf;
import library.infrastructure.datasource.bookonloan.BookOnLoanData;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import library.infrastructure.datasource.bookonloan.ReturnBookData;
import library.infrastructure.datasource.holding.HoldingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CounterDataSource implements CounterRepository {
    HoldingMapper holdingMapper;
    BookOnLoanMapper bookOnLoanMapper;

    public CounterDataSource(HoldingMapper holdingMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.holdingMapper = holdingMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public Counter counter(BookIds bookIds) {
        List<Holding> holdings = holdingMapper.selectHoldingsByBookIds(bookIds.asList());
        Catalog catalog = new Catalog(holdings);

        return new Counter(catalog, libraryCardShelf(catalog), retentionShelf());
    }

    private RetentionShelf retentionShelf() {
        return null;
    }

    private LibraryCardShelf libraryCardShelf(Catalog catalog) {
        List<BookOnLoanData> bookOnLoansDataList = bookOnLoanMapper.selectByHoldingCodes(catalog.holdingsCodes());
        List<ReturnBookData> returnBookDataList = bookOnLoanMapper.selectReturnedBookByHoldingCodes(catalog.holdingsCodes());

        List<LibraryCard> libraryCards = catalog.holdingsCodes().stream().map(holdingCode -> {
            List<LoaningRecord> loaningRecords = toLoaningRecords(bookOnLoansDataList, holdingCode);
            List<ReturningRecord> returningRecords = toReturningRecords(returnBookDataList, holdingCode);

            return new LibraryCard(holdingCode, new LoaningHistory(loaningRecords), new ReturningHistory(returningRecords));
        }).collect(Collectors.toList());

        return new LibraryCardShelf(libraryCards);
    }

    private List<LoaningRecord> toLoaningRecords(List<BookOnLoanData> bookOnLoansDataList, HoldingCode holdingCode) {
        return bookOnLoansDataList.stream()
                .filter(bookOnLoanData -> bookOnLoanData.holdingCode().sameValue(holdingCode))
                .map(BookOnLoanData::toLoaningRecord)
                .collect(Collectors.toList());
    }

    private List<ReturningRecord> toReturningRecords(List<ReturnBookData> returnBookDataList, HoldingCode holdingCode) {
        return returnBookDataList.stream()
                .filter(returnBookData -> returnBookData.holdingCode().sameValue(holdingCode))
                .map(ReturnBookData::toReturningRecord)
                .collect(Collectors.toList());
    }
}