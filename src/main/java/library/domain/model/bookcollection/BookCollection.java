package library.domain.model.bookcollection;

import library.domain.model.book.Book;
import library.domain.model.bookonloan.LoanPeriod;

/**
 * 蔵書
 */
public class BookCollection {
    BookCollection bookCollection;
    Book book;
    BookCollectionStatus bookCollectionStatus;
    LoanPeriod loanPeriod;
}
