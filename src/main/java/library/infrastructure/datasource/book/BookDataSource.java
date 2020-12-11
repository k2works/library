package library.infrastructure.datasource.book;

import library.application.repository.BookRepository;
import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.item.bibliography.NumberOfBook;
import library.domain.model.reservation.availability.BookAvailabilities;
import library.domain.model.reservation.availability.BookAvailability;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDataSource implements BookRepository {
    BookMapper bookMapper;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BookDataSource(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookAvailabilities search(Keyword keyword) {
        List<BookAvailability> books = bookMapper.searchBooks(keyword, NumberOfBook.MAX_TO_SHOW + 1);
        return new BookAvailabilities(books);
    }

    @Override
    public Book findBook(BookNumber bookNumber) {
        return bookMapper.findBook(bookNumber);
    }
}
