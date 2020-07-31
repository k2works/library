package library.domain.model.reservation.retention;

import library.domain.model.item.bibliography.本;

/**
 * 蔵書と本の照合結果
 */
public enum BookMatching {
    一致("この蔵書は該当の書籍です"),
    不一致("この蔵書は該当の書籍ではありません");

    String description;

    BookMatching(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public static BookMatching isSame(本 one, 本 another) {
        return one.isSame(another) ? 一致 : 不一致;
    }
}
