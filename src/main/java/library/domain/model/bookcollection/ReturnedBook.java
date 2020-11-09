package library.domain.model.bookcollection;

import library.domain.model.bookonloan.BookOnLoan;

/**
 * 返却された貸出図書
 */
public class ReturnedBook {
    BookOnLoan bookOnLoan;
    ReturnDate returnDate;
}
