package library.infrastructure.datasource.loan;

import library.LibraryDBTest;
import library.application.service.返却.返却登録サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.返却;
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
    LoanDataSource loanDataSource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    返却登録サービス 返却登録サービス;

    @Test
    void 蔵書番号で貸出を取得できる() throws Exception {
        mockMvc.perform(
                post("/loan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-C")
                        .param("loanDate.value", "2020-02-14"));

        蔵書番号 蔵書番号 = new 蔵書番号("2-C");
        Loan loan = loanDataSource.findBy(蔵書番号);

        assertEquals("2020-02-14", loan.date().toString());
    }

    @Test
    void 返却した蔵書は取得できない() throws Exception {
        mockMvc.perform(
                post("/loan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-D")
                        .param("loanDate.value", "2020-02-14"));

        蔵書番号 蔵書番号 = new 蔵書番号("2-D");
        ReturnDate returnDate = ReturnDate.parse("2019-01-01");
        返却登録サービス.返却を記録する(new 返却(蔵書番号, returnDate));


        assertThrows(IllegalArgumentException.class, () -> loanDataSource.findBy(蔵書番号));
    }
}