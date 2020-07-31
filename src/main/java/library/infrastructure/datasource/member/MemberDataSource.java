package library.infrastructure.datasource.member;

import library.application.repository.会員リポジトリ;
import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.member.会員登録の状態;
import org.springframework.stereotype.Repository;

import static library.domain.model.member.会員登録の状態.有効;
import static library.domain.model.member.会員登録の状態.未登録;

@Repository
public class MemberDataSource implements 会員リポジトリ {
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MemberDataSource(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public 会員登録の状態 status(会員番号 会員番号) {
        boolean exists = memberMapper.exists(会員番号);
        if (exists) return 有効;

        return 未登録;
    }
    @Override
    public 会員 findBy(会員番号 会員番号) {
        return memberMapper.selectMember(会員番号);
    }
}
