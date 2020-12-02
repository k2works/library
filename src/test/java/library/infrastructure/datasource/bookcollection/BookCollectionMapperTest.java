package library.infrastructure.datasource.bookcollection;

import library.LibraryDBTest;
import library.domain.model.book.BookId;
import library.domain.model.bookcollection.BookCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class BookCollectionMapperTest {

    @Autowired
    BookCollectionMapper bookCollectionMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 本IDで在庫有りの蔵書一覧を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("bookCollectionCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        List<BookCollection> bookCollections = bookCollectionMapper.selectInStockBookCollectionsByBookIds(List.of(new BookId(2)));

        assertEquals(7, bookCollections.size());
    }
}