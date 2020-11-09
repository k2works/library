package library.domain.model.bookcollection;

import library.domain.model.bookonloan.BookOnLoan;

/**
 * 返却された貸出図書
 */
public class ReturnedBook {
    BookOnLoan bookOnLoan;
    ReturnDate returnDate;

    public ReturnedBook(BookOnLoan bookOnLoan, ReturnDate returnDate) {
        this.bookOnLoan = bookOnLoan;
        this.returnDate = returnDate;
    }

    public BookOnLoan bookOnLoan() {
        return bookOnLoan;
    }

    public ReturnDate returnDate() {
        return returnDate;
    }
}
