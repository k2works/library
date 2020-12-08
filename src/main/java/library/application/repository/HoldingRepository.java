package library.application.repository;

import library.domain.model.item.HoldingInStock;
import library.domain.model.item.HoldingOnLoan;
import library.domain.model.item.ItemNumber;

/**
 * 蔵書リポジトリ
 */
public interface HoldingRepository {
    HoldingOnLoan findHoldingOnLoan(ItemNumber itemNumber);

    HoldingInStock findHoldingInStock(ItemNumber itemNumber);
}
