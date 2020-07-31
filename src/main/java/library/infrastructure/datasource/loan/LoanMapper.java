package library.infrastructure.datasource.loan;

import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanNumber;
import library.domain.model.member.会員番号;
import library.domain.model.returned.返却;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    int newLoanNumber();

    List<Loan> selectByMemberNumber(@Param("memberNumber") 会員番号 会員番号);

    void insertLoan(
            @Param("loanNumber") int loanNumber,
            @Param("memberNumber") 会員番号 会員番号,
            @Param("itemNumber") 蔵書番号 蔵書番号,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnBook(
            @Param("loanNumber") LoanNumber loanNumber,
            @Param("returned") 返却 返却);

    Optional<Loan> selectByItemNumber(@Param("itemNumber") 蔵書番号 蔵書番号);

    List<Loan> selectByItemNumbers(@Param("itemNumbers") List<蔵書番号> 蔵書番号s);

    List<返却> selectReturnedByItemNumbers(@Param("itemNumbers") List<蔵書番号> 蔵書番号s);
}
