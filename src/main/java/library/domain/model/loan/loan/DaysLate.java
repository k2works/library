package library.domain.model.loan.loan;

import library.domain.type.date.Days;

/**
 * 遅延日数
 */
public class DaysLate {
    Days value;

    public DaysLate(Days value) {
        this.value = value;
    }

    public int intValue() {
        return value.value();
    }

    public DelayStatus delayStatus() {
        return DelayStatus.level(value);
    }

    static DaysLate from(long delays) {
        Days days = new Days((int) delays);
        return new DaysLate(days);
    }
}