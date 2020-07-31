package library.domain.model.reservation.retention;

import library.domain.model.item.蔵書番号;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.貸出依頼;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.予約番号;

/**
 * 準備完了
 */
public class 準備完了 {
    貸出予約 貸出予約;
    RetainedDate retainedDate;
    蔵書番号 蔵書番号;

    public boolean isExpired() {
        ExpireDate expireDate = ExpireDate.of(retainedDate);
        return expireDate.isExpired();
    }

    public 予約番号 reservationNumber() {
        return 貸出予約.number();
    }
    public String showExpireDate() {
        return ExpireDate.of(retainedDate).show();
    }

    public String showBook() {
        return 貸出予約.showBook();
    }

    public 蔵書番号 itemNumber() {
        return 蔵書番号;
    }

    public 会員番号 memberNumber() {
        return 貸出予約.memberNumber();
    }

    public 貸出依頼 貸出依頼を作る() {
        return new 貸出依頼(貸出予約.memberNumber(), 蔵書番号, LoanDate.from(retainedDate.value));
    }

    @Override
    public String toString() {
        return "Retained{" +
                ", reservation=" + 貸出予約 +
                ", retainedDate=" + retainedDate +
                ", itemNumber=" + 蔵書番号 +
                '}';
    }
}
