package library.application.service.book;

import library.application.repository.BookRepository;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.book.Books;
import org.springframework.stereotype.Service;

/**
 * 本検索サービス
 */
@Service
public class BookQueryService {
    BookRepository bookRepository;

    BookQueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 本検索
     */
    public Books search(BookSearchKeyword keyword) {
        return bookRepository.search(keyword);
    }
}
