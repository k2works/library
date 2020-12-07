package library.domain.model.counter;

import library.domain.model.book.BookId;
import library.domain.model.holding.Holding;

/**
 * 目録
 */
public class Catalog {
    BookId bookId;
    Holding holding;

    public Catalog(BookId bookId, Holding holding) {
        this.bookId = bookId;
        this.holding = holding;
    }

    public boolean sameBook(BookId bookId) {
        return this.bookId.sameValue(bookId);
    }
}
