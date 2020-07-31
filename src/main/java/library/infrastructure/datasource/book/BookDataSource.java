package library.infrastructure.datasource.book;

import library.application.repository.本リポジトリ;
import library.domain.model.item.bibliography.*;
import library.domain.model.reservation.availability.本の一覧と貸出可否;
import library.domain.model.reservation.availability.BookAvailability;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDataSource implements 本リポジトリ {
    BookMapper bookMapper;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BookDataSource(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public 本の一覧と貸出可否 探す(キーワード キーワード) {
        List<BookAvailability> books = bookMapper.searchBooks(キーワード, NumberOfBook.MAX_TO_SHOW + 1);
        return new 本の一覧と貸出可否(books);
    }

    @Override
    public 本 見つける(書籍番号 書籍番号) {
        return bookMapper.findBook(書籍番号);
    }
}
