package library.domain.model.member;

/**
 * 会員番号
 */
public class MemberNumber {
    Integer value;

    public MemberNumber(Integer value) {
        this.value = value;
    }

    @Deprecated
    MemberNumber() {
    }

    public static MemberNumber empty() {
        return new MemberNumber();
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        if (value == null || value == 0) return "";
        return Integer.toString(value);
    }
}
