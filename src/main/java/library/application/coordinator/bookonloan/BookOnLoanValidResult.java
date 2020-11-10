package library.application.coordinator.bookonloan;

import library.domain.model.bookonloan.loaning.CanLoan;

/**
 * 貸出図書 検証エラー
 */
// TODO: 登録結果を返すクラスに変更する
public enum BookOnLoanValidResult {
    貸出可能("", CanLoan.貸出可能),
    貸出中の蔵書("現在貸出中の蔵書です。", null),
    貸出制限エラー("これ以上本を貸し出すことができません。", CanLoan.貸出不可);

    String message;
    CanLoan canLoan;

    BookOnLoanValidResult(String message, CanLoan canLoan) {
        this.message = message;
        this.canLoan = canLoan;
    }

    static public BookOnLoanValidResult bookOnLoanValidResult(CanLoan canLoan) throws IllegalAccessException {
        for (BookOnLoanValidResult value : values()) {
            if (value.canLoan == canLoan)
                return value;
        }

        throw new IllegalAccessException("存在しないEnum値");
    }

    public String message() {
        return message;
    }

    public boolean hasError() {
        return this != 貸出可能;
    }
}
