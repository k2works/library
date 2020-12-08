package library.application.service.bookonloan;

import library.application.repository.LoanRepository;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.LoanRequest;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録サービス
 */
@Service
public class LoanRecordService {
    LoanRepository loanRepository;

    LoanRecordService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * 貸出図書を登録する
     */
    public Loan registerBookOnLoan(LoanRequest loanRequest) {
        return loanRepository.registerBookOnLoan(loanRequest);
    }
}