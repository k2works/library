package library.domain.model.loan.loan;

/**
 * 貸出図書ID
 */
public class LoanNumber {
    Integer value;

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
