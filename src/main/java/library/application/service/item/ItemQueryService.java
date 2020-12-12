package library.application.service.item;

import library.application.repository.ItemRepository;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import org.springframework.stereotype.Service;

/**
 * 蔵書検索サービス
 */
@Service
public class ItemQueryService {
    ItemRepository itemRepository;

    public ItemQueryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     *
     */
    public ItemStatus status(ItemNumber itemNumber) {
        return itemRepository.status(itemNumber);
    }

    /**
     * 蔵書を見つける
     */
    public Item findBy(ItemNumber itemNumber) {
        return itemRepository.findBy(itemNumber);
    }
}
