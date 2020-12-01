package library.domain.model.bookonloan.loaning;

/**
 * メッセージ
 */
public class RejectReason {
    String value;

    public RejectReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
