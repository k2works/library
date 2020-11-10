package library.application.service.book;

import library.application.repository.BookRepository;
import library.domain.model.book.Books;
import library.domain.model.book.SearchKeyword;

/**
 * 本検索サービス
 */
public class BookQueryService {
    BookRepository bookRepository;

    BookQueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 本検索
     */
    public Books search(SearchKeyword keyword) {
        return bookRepository.search(keyword);
    }
}
