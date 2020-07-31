package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.type.date.CurrentDate;

/**
 * 貸出状況
 */
public class 貸出状況 {
    会員 会員;
    Loans loans;
    CurrentDate date;

    public 貸出状況(会員 会員, Loans loans, CurrentDate date) {
        this.会員 = 会員;
        this.loans = loans;
        this.date = date;
    }

    public 貸出可否 shouldRestrict() {
        Restriction restriction = new Restriction(会員, loans, date);
        RestrictionOfQuantity restrictionOfQuantity = restriction.ofQuantity();
        return restrictionOfQuantity.shouldRestrict(loans);
    }

    public int count() {
        return loans.count();
    }

    public 会員番号 memberNumber() {
        return 会員.number();
    }

    public Loans loans() {
        return loans;
    }

    @Override
    public String toString() {
        return "LoanStatus{" +
                "member=" + 会員 +
                ", loans=" + loans +
                ", date=" + date +
                '}';
    }
}
