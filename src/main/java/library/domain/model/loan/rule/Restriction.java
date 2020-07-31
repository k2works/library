package library.domain.model.loan.rule;

import library.domain.model.loan.delay.DelayStatus;
import library.domain.model.loan.Loans;
import library.domain.model.member.会員;
import library.domain.type.date.CurrentDate;

/**
 * 貸出制限
 */
class Restriction {
    会員 会員;
    Loans loans;
    CurrentDate date;

    Restriction(会員 会員, Loans loans, CurrentDate date) {
        this.会員 = 会員;
        this.loans = loans;
        this.date = date;
    }

    static final RestrictionMap map = new RestrictionMap();

    RestrictionOfQuantity ofQuantity() {
        DelayStatus delayStatus = loans.worst(date);
        DelayOfMember delayOfMember = new DelayOfMember(delayStatus, 会員.type());
        return map.of(delayOfMember);
    }
}
