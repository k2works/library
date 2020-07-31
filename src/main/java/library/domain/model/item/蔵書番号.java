package library.domain.model.item;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書番号
 */
public class 蔵書番号 {
    @NotBlank(message = "蔵書番号を入力してください。")
    String value;

    public 蔵書番号(String value) {
        this.value = value;
    }

    @Deprecated
    蔵書番号() {
    }

    public boolean sameValue(蔵書番号 other) {
        return value.equals(other.value);
    }

    public static 蔵書番号 empty() {
        return new 蔵書番号("");
    }
    @Override
    public String toString() {
        return value;
    }
}
