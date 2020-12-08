package library.infrastructure.datasource.loan;

import library.application.repository.LoanRepository;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.loan.Returned;
import library.domain.model.loan.rule.LoanRequest;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.member.Member;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LoanDataSource implements LoanRepository {
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
    public Loan registerLoan(LoanRequest loanRequest) {
        ItemNumber itemNumber = loanRequest.item().itemNumber();
        itemMapper.lockItem(itemNumber);

        if (loanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new RegisterLoanException(loanRequest);
        }

        Integer bookOnLoanId = loanMapper.newLoanNumber();
        loanMapper.insertLoan(
                bookOnLoanId,
                loanRequest.member().memberNumber(),
                loanRequest.item().itemNumber(),
                loanRequest.loanDate());

        return findLoanByItemNumber(itemNumber);
    }

    @Override
    public void registerReturnBook(Returned returned) {
        Loan loan = returned.bookOnLoan();
        loanMapper.insertReturnBook(loan.loanNumber(), returned.returnDate());
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        List<Loan> loanDataList = loanMapper.selectByMemberNumber(member.memberNumber());
        List<Loan> loans = bookOnLoans(member, loanDataList);

        return new MemberAllBookOnLoans(member, new Loans(loans));
    }

    @Override
    public Loan findLoanByItemNumber(ItemNumber itemNumber) {
        Loan loan = loanMapper.selectByItemNumber(itemNumber).orElseThrow(() ->
                new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber)));

        Member member = memberMapper.selectMember(loan.memberNumber());
        Loan loanResult = bookOnLoans(member, List.of(loan)).get(0);
        return loanResult;
    }

    List<Loan> bookOnLoans(Member member, List<Loan> loanDataList) {
        if (loanDataList.isEmpty()) return List.of();

        List<ItemNumber> itemNumbers =
                loanDataList.stream()
                        .map(loanData -> loanData.itemNumber())
                        .collect(Collectors.toList());
        List<Item> items = itemMapper.selectItems(itemNumbers);

        return loanDataList.stream()
                .map(loanData ->
                        items.stream()
                                .filter(holding -> holding.itemNumber().sameValue(loanData.itemNumber()))
                                .findFirst()
                                .map(item -> new Loan(loanData.loanNumber(), member, item, loanData.loanDate()))
                                .orElseThrow())
                .collect(Collectors.toList());
    }


}
