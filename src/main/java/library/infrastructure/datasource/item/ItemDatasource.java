package library.infrastructure.datasource.item;

import library.application.repository.ItemRepository;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import org.springframework.stereotype.Repository;

import static library.domain.model.item.ItemStatus.*;

@Repository
public class ItemDatasource implements ItemRepository {
    ItemMapper itemMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ItemDatasource(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemStatus status(ItemNumber itemNumber) {
        if (! itemMapper.exists(itemNumber)) return 未登録;
        if (itemMapper.loanable(itemNumber)) return 貸出可能;
        if (itemMapper.loaned(itemNumber)) return 貸出中;
        if (itemMapper.retained(itemNumber)) return 取置中;
        return 貸出不可;
    }
    @Override
    public Item findBy(ItemNumber itemNumber) {
        return itemMapper.selectItem(itemNumber);
    }
}
