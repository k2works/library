package library.application.repository;

import library.domain.model.book.item.ItemInStock;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.ItemOnLoan;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    ItemOnLoan findHoldingOnLoan(ItemNumber itemNumber);

    ItemInStock findHoldingInStock(ItemNumber itemNumber);
}
