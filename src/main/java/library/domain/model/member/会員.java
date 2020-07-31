package library.domain.model.member;

/**
 * *会員
 */
public class 会員 {
    会員番号 会員番号;
    Name name;
    MemberType memberType;

    public 会員(会員番号 会員番号, Name name, MemberType memberType) {
        this.会員番号 = 会員番号;
        this.name = name;
        this.memberType = memberType;
    }

    @Deprecated
    会員() {
    }

    public 会員番号 number() {
        return 会員番号;
    }

    public Name name() {
        return name;
    }

    public MemberType type() {
        return memberType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNumber=" + 会員番号 +
                ", name=" + name +
                ", memberType=" + memberType +
                '}';
    }
}
