package library.infrastructure.datasource.loan;

import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.loan.LoanNumber;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    Integer newBookOnLoanIdentifier();

    List<LoanData> selectByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);

    void insertBookOnLoan(
            @Param("loanNumber") Integer bookOnLoanId,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnBook(
            @Param("loanNumber") LoanNumber loanNumber,
            @Param("returnDate") ReturnDate returnDate);

    Optional<LoanData> selectByItemNumber(@Param("itemNumber") ItemNumber itemNumber);

    List<LoanData> selectByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);

    List<ReturnedData> selectReturnedBookByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);
}
