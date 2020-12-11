package library.application.service.item;

import library.application.repository.ItemRepository;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import org.springframework.stereotype.Service;

/**
 * 蔵書参照サービス
 */
@Service
public class ItemQueryService {
    ItemRepository itemRepository;

    ItemQueryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * 貸出中の蔵書を見つける
     */
    public Item findItemOnLoan(ItemNumber itemNumber) {
        return itemRepository.findItemOnLoan(itemNumber);
    }

    /**
     * 貸出可能な蔵書を見つける
     */
    public Item findItemInStock(ItemNumber itemNumber) {
        return itemRepository.findItemInStock(itemNumber);
    }
}
