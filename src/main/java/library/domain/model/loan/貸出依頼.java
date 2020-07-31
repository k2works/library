package library.domain.model.loan;

import library.domain.model.item.蔵書番号;
import library.domain.model.member.会員番号;

import javax.validation.Valid;

/**
 * 貸出依頼
 */
public class 貸出依頼 {

    @Valid
    会員番号 会員番号;

    @Valid
    蔵書番号 蔵書番号;

    @Valid
    LoanDate loanDate = LoanDate.now();

    public 貸出依頼(@Valid 会員番号 会員番号, @Valid 蔵書番号 蔵書番号, @Valid LoanDate loanDate) {
        this.会員番号 = 会員番号;
        this.蔵書番号 = 蔵書番号;
        this.loanDate = loanDate;
    }

    @Deprecated
    貸出依頼() {
    }
    public 会員番号 会員番号() {
        return 会員番号;
    }

    public 蔵書番号 itemNumber() {
        return 蔵書番号;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public static 貸出依頼 empty() {
        return new 貸出依頼();
    }

    @Override
    public String toString() {
        return "LoanRequestForm{" +
                "memberNumber=" + 会員番号 +
                ", itemNumber=" + 蔵書番号 +
                ", loanDate=" + loanDate +
                '}';
    }
}
