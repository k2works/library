package library.application.repository;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.item.bibliography.Books;
import library.domain.model.item.bibliography.Keyword;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(Keyword keyword);

    Book findBook(BookNumber bookNumber);
}
