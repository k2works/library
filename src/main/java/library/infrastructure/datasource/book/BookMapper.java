package library.infrastructure.datasource.book;

import library.domain.model.item.bibliography.キーワード;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.本;
import library.domain.model.reservation.availability.BookAvailability;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookAvailability> searchBooks(
            @Param("keyword") キーワード キーワード,
            @Param("limit") int limit
    );

    本 findBook(@Param("bookNumber") 書籍番号 書籍番号);
}
