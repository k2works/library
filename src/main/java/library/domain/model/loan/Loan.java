package library.domain.model.loan;

import library.domain.model.item.蔵書;
import library.domain.model.loan.delay.DaysLate;
import library.domain.model.member.会員;
import library.domain.type.date.CurrentDate;
import library.domain.model.member.会員番号;

/**
 * *貸出
 */
public class Loan {
    LoanNumber loanNumber;
    会員 会員;
    蔵書 蔵書;
    LoanDate loanDate;

    @Deprecated
    Loan() {
    }

    public Loan(LoanNumber loanNumber, 会員 会員, 蔵書 蔵書, LoanDate loanDate) {
        this.loanNumber = loanNumber;
        this.会員 = 会員;
        this.蔵書 = 蔵書;
        this.loanDate = loanDate;
    }

    public DaysLate daysLate(CurrentDate date) {
        DueDate dueDate = DueDate.from(loanDate);
        return dueDate.daysLate(date.value());
    }

    public 会員 member() {
        return 会員;
    }

    public LoanDate date() {
        return loanDate;
    }

    public LoanNumber loanNumber() {
        return loanNumber;
    }

    public 蔵書 item() {
        return 蔵書;
    }
    public 会員番号 memberNumber() {
        return 会員.number();
    }
}
