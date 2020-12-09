package library.domain.model.loan.rule;

import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.loan.loan.Loans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
import library.domain.type.date.Date;

/**
 * 会員の全貸出図書
 */
public class CurrentLoans {
    Member member;
    Loans loans;

    public CurrentLoans(Member member, Loans loans) {
        this.member = member;
        this.loans = loans;
    }

    public Restriction canBorrowBookToday() {
        RestrictionType restrictionType = todayLoanRestrictions();
        return restrictionType.canLoan(this.loans);
    }

    RestrictionType todayLoanRestrictions() {
        Date today = Date.now();
        return loanRestrictions(today);
    }

    RestrictionType loanRestrictions(Date date) {
        DelayStatus delayStatus = loans.worst(date);
        MemberType memberType = member.memberType();

        if (memberType == MemberType.大人 && delayStatus == DelayStatus.遅延日数３日未満) {
            return RestrictionType.貸出５冊まで;
        }

        if (memberType == MemberType.子供) {
            if (delayStatus == DelayStatus.遅延日数３日未満) {
                return RestrictionType.貸出７冊まで;
            }

            if (delayStatus == DelayStatus.遅延日数７日未満) {
                return RestrictionType.貸出４冊まで;
            }
        }

        return RestrictionType.貸出不可;
    }

    public Loans bookOnLoans() {
        return loans;
    }

    public Member member() {
        return member;
    }
}
