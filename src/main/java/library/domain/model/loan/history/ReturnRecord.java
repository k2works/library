package library.domain.model.loan.history;

import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.member.MemberNumber;

/**
 * 返却記録の一覧
 */
public class ReturnRecord {
    MemberNumber memberNumber;
    ReturnDate returnDate;

    public ReturnRecord(MemberNumber memberNumber, ReturnDate returnDate) {
        this.memberNumber = memberNumber;
        this.returnDate = returnDate;
    }
}
