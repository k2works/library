package library.domain.model.loan.rule;

import library.domain.model.book.item.Item;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 図書の貸出申請
 */
public class LoanRequest {
    @Valid
    Member member;

    @Valid
    Item item;

    @Valid
    LoanDate loanDate;

    @Deprecated
    LoanRequest() {
    }

    public LoanRequest(Member member, Item itemInStock, LoanDate loanDate) {
        this.member = member;
        this.item = itemInStock;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public Item holdingInStock() {
        return item;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}
