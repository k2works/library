package library.domain.model.reservation.reservation;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class Reservations {
    List<Reservation> list;

    public Reservations(List<Reservation> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<Reservation> asList() {
        return list;
    }
}
