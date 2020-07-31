package library.domain.model.loan.rule;

/**
 * 貸出可否
 */
public enum 貸出可否 {
    貸出不可("これ以上本を貸し出すことができません。"),
    貸出可能("");

    String message;

    貸出可否(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
