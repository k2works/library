package library.domain.type.date;

/**
 * 日数
 */
public class Days {
    int value;

    public Days(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean 未満(int other) {
        return value < other;
    }

    public boolean 以上(int other) {
        return value >= other;
    }
}
