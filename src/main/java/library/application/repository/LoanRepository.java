package library.application.repository;

import library.domain.model.item.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.returned.Returned;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.member.MemberNumber;

/**
 * 貸出リポジトリ
 */
public interface LoanRepository {

    void loan(LoanRequest loanRequest);

    LoanStatus status(MemberNumber memberNumber);

    Loan findBy(ItemNumber itemNumber);

    void returned(Returned returned);
}
