package library.domain.model.member;

import javax.validation.constraints.NotNull;

/**
 * 会員番号
 */
public class 会員番号 {
    @NotNull(message = "会員番号を入力してください。")
    Integer value;

    public 会員番号(Integer value) {
        this.value = value;
    }

    @Deprecated
    会員番号() {
    }

    public Integer value() {
        return value;
    }

    public static 会員番号 empty() {
        return new 会員番号();
    }
    @Override
    public String toString() {
        if (value == null || value == 0) return "";
        return Integer.toString(value);
    }
}
