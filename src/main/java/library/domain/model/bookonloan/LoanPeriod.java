package library.domain.model.bookonloan;

import library.domain.type.date.Date;

/**
 * 貸出期限
 */
public class LoanPeriod {
    Date value;

    public LoanPeriod(Date value) {
        this.value = value;
    }

    public LoanPeriod loanPeriod() {
        return new LoanPeriod(value.plusDays(14));
    }
}
