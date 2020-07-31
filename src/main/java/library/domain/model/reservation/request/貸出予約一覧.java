package library.domain.model.reservation.request;

import java.util.List;

/**
 * 貸出予約リスト
 */
public class 貸出予約一覧 {
    List<貸出予約> list;

    public 貸出予約一覧(List<貸出予約> list) {
        this.list = list;
    }

    public NumberOfReservation numberOfReservation() {
        return new NumberOfReservation(list.size());
    }

    public List<貸出予約> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "list=" + list +
                '}';
    }
}
