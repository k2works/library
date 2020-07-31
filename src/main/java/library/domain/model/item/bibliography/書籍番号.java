package library.domain.model.item.bibliography;

import javax.validation.constraints.NotNull;

/**
 * 書籍番号
 */
public class 書籍番号 {
    @NotNull(message = "本IDを入力してください。")
    int value;

    @Deprecated
    書籍番号() {
    }

    public 書籍番号(int value) {
        this.value = value;
    }
    public 書籍番号(String value) {
        this.value = Integer.parseInt(value);
    }

    public int value() {
        return value;
    }

    public boolean sameValue(書籍番号 other) {
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
