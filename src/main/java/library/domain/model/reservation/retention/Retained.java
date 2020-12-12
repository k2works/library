package library.domain.model.reservation.retention;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.type.date.Date;

/**
 * 取置
 */
public class Retained {
    ReservationNumber reservationNumber;
    ItemNumber itemNumber;
    Reservation reservation;
    RetainedDate retainedDate;
    Item item;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        Date today = Date.now();
        return retainedDate.value.isBefore(today);
    }

    public boolean isA(Item item) {
        return item.itemNumber().sameValue(this.item.itemNumber());
    }

    public Reservation reservedBook() {
        return reservation;
    }

    public RetainedDate retainedDate() {
        return retainedDate;
    }

    public Item holding() {
        return item;
    }

    @Override
    public String toString() {
        return "Retained{" +
                "reservationNumber=" + reservationNumber +
                ", itemNumber=" + itemNumber +
                ", reservation=" + reservation +
                ", retainedDate=" + retainedDate +
                ", item=" + item +
                '}';
    }
}
