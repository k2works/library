package library.infrastructure.datasource.loan;

import library.application.repository.貸出リポジトリ;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.loan.Loans;
import library.domain.model.loan.貸出依頼;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.returned.返却;
import library.domain.model.loan.rule.貸出状況;
import library.domain.type.date.CurrentDate;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class LoanDataSource implements 貸出リポジトリ {
    LoanMapper loanMapper;
    ItemMapper itemMapper;
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public LoanDataSource(LoanMapper loanMapper, ItemMapper itemMapper, MemberMapper memberMapper) {
        this.loanMapper = loanMapper;
        this.itemMapper = itemMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public void loan(貸出依頼 貸出依頼) {
        蔵書番号 蔵書番号 = 貸出依頼.itemNumber();

        if (loanMapper.selectByItemNumber(蔵書番号).isPresent()) {
            throw new RegisterLoanException(貸出依頼);
        }

        int loanNumber = loanMapper.newLoanNumber();
        loanMapper.insertLoan(
                loanNumber,
                貸出依頼.会員番号(),
                蔵書番号,
                貸出依頼.loanDate());

        itemMapper.delete貸出可能(蔵書番号);
        itemMapper.insert貸出中(蔵書番号);
    }

    @Override
    @Transactional
    public void 返却完了(返却 返却) {
        蔵書番号 蔵書番号 = 返却.itemNumber();
        Loan loan = loanMapper.selectByItemNumber(蔵書番号)
                .orElseThrow(() -> new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書番号：%s", 蔵書番号)));
        loanMapper.insertReturnBook(loan.loanNumber(), 返却);

        itemMapper.insert貸出可能(蔵書番号);
        itemMapper.delete貸出中(蔵書番号);
    }


    @Override
    public 貸出状況 status(会員番号 会員番号) {
        List<Loan> loans = loanMapper.selectByMemberNumber(会員番号);
        会員 会員 = memberMapper.selectMember(会員番号);
        return new 貸出状況(会員, new Loans(loans), new CurrentDate(LocalDate.now()));
    }

    @Override
    public Loan findBy(蔵書番号 蔵書番号) {
        return loanMapper.selectByItemNumber(蔵書番号).orElseThrow(() ->
                new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書番号：%s", 蔵書番号)));
    }
}
