package library.infrastructure.datasource.bookonloan;

import library.LibraryDBTest;
import library.application.coordinator.returnbook.ReturnBookCoordinator;
import library.application.service.holding.ItemQueryService;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.type.date.Date;
import library.infrastructure.datasource.loan.LoanDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class LoanDataSourceTest {

    @Autowired
    LoanDataSource bookOnLoanDataSource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReturnBookCoordinator returnBookCoordinator;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 蔵書コードで貸出図書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-C")
                        .param("loanDate.value", "2020-02-14"));

        ItemNumber itemNumber = new ItemNumber("2-C");
        Loan loan = bookOnLoanDataSource.findBookOnLoanByItemNumber(itemNumber);

        assertEquals("2020-02-14", loan.loanDate().toString());
    }

    @Test
    void 返却した蔵書は取得できない() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-D")
                        .param("loanDate.value", "2020-02-14"));

        ItemNumber itemNumber = new ItemNumber("2-D");
        returnBookCoordinator.returnBook(itemNumber, new ReturnDate(Date.from("2019-01-01")));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanDataSource.findBookOnLoanByItemNumber(itemNumber);
        });
    }
}