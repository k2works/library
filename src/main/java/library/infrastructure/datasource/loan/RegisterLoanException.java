package library.infrastructure.datasource.loan;

import library.domain.model.loan.貸出依頼;

public class RegisterLoanException extends RuntimeException {
    貸出依頼 貸出依頼;

    public RegisterLoanException(貸出依頼 貸出依頼) {
        super("貸出中の蔵書に対して貸出登録が行われました。");
        this.貸出依頼 = 貸出依頼;
    }
}
