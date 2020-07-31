package library.infrastructure.datasource.item;

import library.application.repository.蔵書リポジトリ;
import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書番号;
import library.domain.model.item.蔵書の状態;
import org.springframework.stereotype.Repository;

import static library.domain.model.item.蔵書の状態.*;

@Repository
public class ItemDatasource implements 蔵書リポジトリ {
    ItemMapper itemMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ItemDatasource(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public 蔵書の状態 status(蔵書番号 蔵書番号) {
        if (! itemMapper.exists(蔵書番号)) return 未登録;
        if (itemMapper.loanable(蔵書番号)) return 貸出可能;
        if (itemMapper.loaned(蔵書番号)) return 貸出中;
        if (itemMapper.retained(蔵書番号)) return 取置中;
        return 貸出不可;
    }
    @Override
    public 蔵書 findBy(蔵書番号 蔵書番号) {
        return itemMapper.selectItem(蔵書番号);
    }
}
