package library.application.coordinator.returnbook;

import library.application.ExecutionResult;
import library.domain.model.bookcollection.BookCollectionStatus;
import library.domain.model.bookonloan.loaning.RejectReason;

/**
 * 返却結果
 */
public class ReturnBookResult {
    ExecutionResult result;
    RejectReason rejectReason;

    public ReturnBookResult(ExecutionResult result, RejectReason rejectReason) {
        this.result = result;
        this.rejectReason = rejectReason;
    }

    // FIXME:
    static public ReturnBookResult from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.在庫中) {
            return null;
        }

        return null;
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean hasError() {
        // FIXME:
        return false;
    }

    public boolean ok() {
        // FIXME:
        return false;
    }
}