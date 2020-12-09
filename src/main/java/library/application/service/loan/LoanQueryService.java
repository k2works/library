package library.application.service.loan;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.Member;
import org.springframework.stereotype.Service;

/**
 * 貸出参照サービス
 */
@Service
public class LoanQueryService {
    LoanRepository loanRepository;

    LoanQueryService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 会員の全貸出図書を一覧する
     */
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        return loanRepository.findMemberAllBookOnLoans(member);
    }

    /**
     * 蔵書の貸出を見つける
     */
    public Loan findLoanByItemNumber(ItemNumber itemNumber) {
        return loanRepository.findLoanByItemNumber(itemNumber);
    }
}