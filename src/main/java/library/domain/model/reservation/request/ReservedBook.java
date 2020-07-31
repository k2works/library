package library.domain.model.reservation.request;

import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.本;

/**
 * 予約図書
 */
public class ReservedBook {
    本 本;

    public ReservedBook(本 本) {
        this.本 = 本;
    }

    @Deprecated
    ReservedBook() {
    }

    public 本 book() {
        return 本;
    }

    public boolean isA(書籍番号 書籍番号) {
        return 本.bookNumber().sameValue(書籍番号);
    }

    @Override
    public String toString() {
        return "ReservedBook{" +
                "book=" + 本 +
                '}';
    }
}
