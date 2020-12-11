package library.infrastructure.datasource.book;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.reservation.availability.BookAvailability;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookAvailability> searchBooks(
            @Param("keyword") Keyword keyword,
            @Param("limit") int limit
    );

    Book findBook(@Param("bookNumber") BookNumber bookNumber);
}
