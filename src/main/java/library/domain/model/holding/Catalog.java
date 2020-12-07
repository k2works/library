package library.domain.model.holding;

import library.domain.model.book.BookId;

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
