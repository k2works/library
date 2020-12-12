package library.infrastructure.datasource.item;

import library.domain.model.item.ItemNumber;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    void insert貸出可能(ItemNumber itemNumber);
    void delete貸出可能(ItemNumber itemNumber);
}
