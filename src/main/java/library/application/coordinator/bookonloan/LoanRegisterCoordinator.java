package library.application.coordinator.bookonloan;

import library.application.service.bookonloan.LoanQueryService;
import library.application.service.bookonloan.LoanRecordService;
import library.application.service.holding.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.rule.*;
import org.springframework.stereotype.Service;

/**
 * 貸出図書登録コーディネーター
 */
@Service
public class LoanRegisterCoordinator {
    MemberQueryService memberQueryService;
    ItemQueryService itemQueryService;
    LoanQueryService loanQueryService;
    LoanRecordService loanRecordService;

    public LoanRegisterCoordinator(
            MemberQueryService memberQueryService,
            ItemQueryService itemQueryService,
            LoanQueryService loanQueryService,
            LoanRecordService loanRecordService) {
        this.memberQueryService = memberQueryService;
        this.itemQueryService = itemQueryService;
        this.loanQueryService = loanQueryService;
        this.loanRecordService = loanRecordService;
    }

    /**
     * 図書の貸出を受付る
     */
    public LoaningCard loaning(LoanRequest loanRequest) {
        MemberAllBookOnLoans memberAllBookOnLoans = loanQueryService.findMemberAllBookOnLoans(loanRequest.member());

        if (memberAllBookOnLoans.canBorrowBookToday() == CanLoan.貸出不可) {
            return new LoaningCard(RejectReason.貸出冊数超過);
        }

        Loan loan = loanRecordService.registerBookOnLoan(loanRequest);
        return new LoaningCard(loan);
    }

}
