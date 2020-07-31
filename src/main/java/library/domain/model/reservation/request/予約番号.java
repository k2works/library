package library.domain.model.reservation.request;

/**
 * 予約番号
 */
public class 予約番号 {
    // TODO: UUIDに変更する
    int value;

    @Deprecated
    予約番号() {
    }

    public 予約番号(String textValue) {
        this.value = Integer.parseInt(textValue);
    }

    public static 予約番号 generate() {
        // TODO: UUID生成処理
        return new 予約番号("0");
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
