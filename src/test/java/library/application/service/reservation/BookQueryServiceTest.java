package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.book.BookQueryService;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Books;
import library.domain.model.book.bibliography.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class BookQueryServiceTest {
    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 本を検索できる() {
        Keyword keyword = new Keyword("ハンドブック");
        Books books = bookQueryService.search(keyword);

        assertAll(
                () -> assertEquals(1, books.size().value()),
                () -> assertEquals(
                        "RDRA2.0 ハンドブック: 軽く柔軟で精度の高い要件定義のモデリング手法",
                        books.asList().get(0).title().toString()));
    }

    @Test
    void 本を取得できる() {
        Book book = bookQueryService.findBook(new BookNumber(1));
        assertEquals(book.bookNumber().value(), 1);
    }
}