package library.domain.model.reservation.reservation;

/**
 * 貸出予約ID
 */
public class ReservationNumber {
    // TODO: UUIDに変更する
    int value;

    @Deprecated
    ReservationNumber() {
    }

    public ReservationNumber(int value) {
        this.value = value;
    }

    public static ReservationNumber generate() {
        // TODO: UUID生成処理
        return new ReservationNumber(0);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "ReservationId{" +
                "value=" + value +
                '}';
    }
}
