package library.domain.model.bookonloan.librarycard;

import java.util.List;

/**
 * 返却履歴
 */
public class ReturningHistory {
    List<ReturningRecord> returningRecords;

    public ReturningHistory(List<ReturningRecord> returningRecords) {
        this.returningRecords = returningRecords;
    }

    public HistoryCount historyCount() {
        return new HistoryCount(returningRecords.size());
    }
}
