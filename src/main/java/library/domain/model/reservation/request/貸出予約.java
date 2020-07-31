package library.domain.model.reservation.request;

import library.domain.model.item.bibliography.本;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;

/**
 * 貸出予約
 */
public class 貸出予約 {
    予約番号 番号;
    会員 会員;
    本 本;

    @Deprecated
    貸出予約() {
    }

    private 貸出予約(予約番号 番号, 会員 会員, 本 本) {
        this.番号 = 番号;
        this.会員 = 会員;
        this.本 = 本;
    }

    public static 貸出予約 する(会員 会員, 本 本) {
        return new 貸出予約(予約番号.generate(), 会員, 本);
    }

    public 本 book() {
        return 本;
    }

    public 会員 member() {
        return 会員;
    }

    public 会員番号 memberNumber() {
        return 会員.number();
    }
    public 書籍番号 bookNumber() {
        return 本.bookNumber();
    }
    public String showBook() {
        return 本.show();
    }

    public 予約番号 number() {
        return 番号;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + 番号 +
                ", member=" + 会員 +
                ", book=" + 本 +
                '}';
    }
}
