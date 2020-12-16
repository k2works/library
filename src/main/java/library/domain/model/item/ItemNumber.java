package library.domain.model.item;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書番号
 */
public class ItemNumber {
    @NotBlank(message = "蔵書番号を入力してください。")
    String value;

    public ItemNumber(String value) {
        this.value = value;
    }

    @Deprecated
    ItemNumber() {
    }

    public boolean sameValue(ItemNumber other) {
        return value.equals(other.value);
    }

    public static ItemNumber empty() {
        return new ItemNumber("");
    }
    @Override
    public String toString() {
        return value;
    }
}
