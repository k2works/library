package library.infrastructure.datasource.item;

import library.domain.model.book.BookId;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HoldingMapper {
    Item selectHolding(@Param("itemNumber") ItemNumber itemNumber);

    List<Item> selectHoldings(@Param("itemNumbers") List<ItemNumber> itemNumbers);

    List<Item> selectHoldingsByBookIds(@Param("bookIds") List<BookId> bookIds);

    ItemNumber lockHolding(@Param("itemNumber") ItemNumber itemNumber);
}
