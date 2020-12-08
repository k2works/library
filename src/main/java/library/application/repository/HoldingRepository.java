package library.application.repository;

import library.domain.model.book.item.HoldingInStock;
import library.domain.model.book.item.HoldingOnLoan;
import library.domain.model.book.item.ItemNumber;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    HoldingOnLoan findHoldingOnLoan(ItemNumber itemNumber);

    HoldingInStock findHoldingInStock(ItemNumber itemNumber);
}
