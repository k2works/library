package library.infrastructure.datasource.item;

import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {
    Item selectItem(@Param("itemNumber") ItemNumber itemNumber);
}
