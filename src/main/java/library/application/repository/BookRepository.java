package library.application.repository;

import library.domain.model.book.BookSearchKeyword;
import library.domain.model.book.Books;

/**
 * 本リポジトリ
 */
public interface BookRepository {

    Books search(BookSearchKeyword keyword);
}
