package library.domain.model.bookonloan.loaning;

import library.application.ExecutionResult;
import library.domain.model.bookcollection.BookCollectionStatus;

/**
 * 貸出票
 */
public class LoaningCard {
    ExecutionResult result;
    RejectReason rejectReason;

    public LoaningCard(ExecutionResult result, RejectReason rejectReason) {
        this.result = result;
        this.rejectReason = rejectReason;
    }

    static public LoaningCard from(CanLoan canLoan) {
        if (canLoan == CanLoan.貸出不可) {
            return new LoaningCard(null, RejectReason.貸出冊数超過);
        }

        return new LoaningCard(null, null);
    }

    static public LoaningCard from(BookCollectionStatus bookCollectionStatus) {
        if (bookCollectionStatus == BookCollectionStatus.貸出中) {
            return new LoaningCard(null, RejectReason.蔵書が貸出中);
        }

        return new LoaningCard(null, null);
    }

    public String message() {
        return rejectReason.toString();
    }

    public boolean hasError() {
        return result == ExecutionResult.NG;
    }

    public boolean ok() {
        return result == ExecutionResult.OK;
    }
}