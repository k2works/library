package library.application.repository;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Books;
import library.domain.model.book.bibliography.Keyword;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(Keyword keyword);

    Book findBook(BookNumber bookNumber);
}
