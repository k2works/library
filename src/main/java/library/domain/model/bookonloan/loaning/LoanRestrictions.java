package library.domain.model.bookonloan.loaning;

import library.domain.model.bookonloan.loan.BookOnLoans;

/**
 * 貸出制限
 */
public enum LoanRestrictions {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    LoanRestrictions(int limit) {
        this.limit = limit;
    }

    public CanLoan canLoan(BookOnLoans bookOnLoans) {
        if (limit > bookOnLoans.numberOfBookOnLoans().value()) {
            return CanLoan.貸出可能;
        }
        return CanLoan.貸出不可;
    }

}
