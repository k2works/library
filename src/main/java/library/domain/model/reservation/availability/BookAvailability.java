package library.domain.model.reservation.availability;

import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.本;

import static library.domain.model.reservation.availability.Availability.予約できる;

/**
 * 本の貸出可否
 */
public class BookAvailability {
    本 本;
    int loanableItems;

    public String showAvailability() {
        return availability().show();
    }

    public boolean isAvailable() {
        return 予約できる == availability();
    }

    private Availability availability() {
        return Availability.availability(loanableItems);
    }

    public String describeBook() {
        return 本.show();
    }

    public 書籍番号 bookNumber() {
        return 本.bookNumber();
    }

    // TODO テスト用：テストを変更して、このメソッドを廃止する
    public 本 book() {
        return 本;
    }

    @Override
    public String toString() {
        return "BookAvailability{" +
                "book=" + 本 +
                ", loanable=" + loanableItems +
                '}';
    }
}
