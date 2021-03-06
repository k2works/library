package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;

/**
 * *冊数制限(判定結果)
 */
enum RestrictionOfQuantity {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    RestrictionOfQuantity(int limit) {
        this.limit = limit;
    }

    public Loanability shouldRestrict(Loans loans) {
        if (limit > loans.count()) {
            return Loanability.貸出可能;
        }
        return Loanability.貸出不可;
    }

}
