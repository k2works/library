package library.domain.model.item;

import library.domain.model.item.bibliography.本;

/**
 * *蔵書
 */
public class 蔵書 {
    蔵書番号 蔵書番号;
    本 本;
    蔵書の状態 status; // TODO 現在は未使用：明示的に持つべきか検討する

    @Deprecated
    蔵書() {
    }

    public 蔵書(蔵書番号 蔵書番号, 本 本) {
        this.蔵書番号 = 蔵書番号;
        this.本 = 本;
    }

    public 本 book() {
        return 本;
    }
    public 蔵書番号 itemNumber() {
        return 蔵書番号;
    }

    public String show() {
        return String.format("[%s] %s",
                蔵書番号.toString(), 本.show());
    }
    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + 蔵書番号 +
                ", book=" + 本 +
                ", status=" + status +
                '}';
    }
}
