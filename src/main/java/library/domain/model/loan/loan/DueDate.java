package library.domain.model.loan.loan;

import library.domain.type.date.Date;

/**
 * 貸出期限
 */
public class DueDate {
    Date value;

    public DueDate(Date value) {
        this.value = value;
    }

    public LoanStatus loanStatus() {
        Date today = Date.now();
        if (value.isBefore(today)) {
            return LoanStatus.期限切れ;
        }
        return LoanStatus.期限内;
    }

    public Date value() {
        return value;
    }
}
