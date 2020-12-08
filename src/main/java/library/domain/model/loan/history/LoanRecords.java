package library.domain.model.loan.history;

import java.util.List;

/**
 * 貸出履歴
 */
public class LoanRecords {
    List<LoanRecord> loanRecords;

    public LoanRecords(List<LoanRecord> loanRecords) {
        this.loanRecords = loanRecords;
    }

    HistoryCount historyCount() {
        return new HistoryCount(loanRecords.size());
    }
}
