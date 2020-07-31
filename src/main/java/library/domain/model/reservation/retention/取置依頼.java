package library.domain.model.reservation.retention;

import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.request.予約番号;

import javax.validation.Valid;

/**
 * 取置依頼
 */
public class 取置依頼 {
    予約番号 予約番号;
    @Valid
    蔵書番号 番号 = 蔵書番号.empty();

    public 予約番号 reservationNumber() {
        return 予約番号;
    }

    public 蔵書番号 蔵書番号() {
        return 番号;
    }

    @Override
    public String toString() {
        return "Retention{" +
                "reservationNumber=" + 予約番号 +
                ", itemNumber=" + 番号 +
                '}';
    }
}
