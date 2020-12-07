package library.domain.model.bookonloan.librarycard;

import java.util.List;

/**
 * 返却履歴
 */
public class ReturningHistory {
    List<ReturningRecord> returningRecords;

    public HistoryCount historyCount() {
        return new HistoryCount(returningRecords.size());
    }
}
