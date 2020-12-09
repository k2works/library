package library.domain.model.loan.returned;

import library.domain.model.book.item.ItemNumber;

/**
 * 返却
 */
public class Returned {
    ItemNumber itemNumber;
    ReturnDate returnDate;

    public Returned(ItemNumber itemNumber, ReturnDate returnDate) {
        this.itemNumber = itemNumber;
        this.returnDate = returnDate;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }
}
