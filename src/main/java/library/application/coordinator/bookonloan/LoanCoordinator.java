package library.application.coordinator.bookonloan;

import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.item.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.rule.*;
import org.springframework.stereotype.Service;

/**
 * 貸出コーディネーター
 */
@Service
public class LoanCoordinator {
    MemberQueryService memberQueryService;
    ItemQueryService itemQueryService;
    LoanQueryService loanQueryService;
    LoanRegisterService loanRegisterService;

    public LoanCoordinator(
            MemberQueryService memberQueryService,
            ItemQueryService itemQueryService,
            LoanQueryService loanQueryService,
            LoanRegisterService loanRegisterService) {
        this.memberQueryService = memberQueryService;
        this.itemQueryService = itemQueryService;
        this.loanQueryService = loanQueryService;
        this.loanRegisterService = loanRegisterService;
    }

    /**
     * 貸出制限を判断する
     */
    public Restriction shouldRestrict(LoanRequest loanRequest) {
        MemberAllBookOnLoans memberAllBookOnLoans = loanQueryService.findMemberAllBookOnLoans(loanRequest.member());
        return memberAllBookOnLoans.canBorrowBookToday();
    }
    /**
     * 貸出を受付る
     */
    public void loan(LoanRequest loanRequest) {
        loanRegisterService.registerLoan(loanRequest);
    }

}
