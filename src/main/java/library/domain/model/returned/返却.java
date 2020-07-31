package library.domain.model.returned;

import library.domain.model.item.蔵書番号;

/**
 * 返却
 * （イベント）
 */
public class 返却 {
    蔵書番号 蔵書番号;
    ReturnDate returnDate;

    public 返却(蔵書番号 蔵書番号, ReturnDate returnDate) {
        this.蔵書番号 = 蔵書番号;
        this.returnDate = returnDate;
    }

    public 蔵書番号 itemNumber() {
        return 蔵書番号;
    }
}
