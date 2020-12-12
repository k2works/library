package library.infrastructure.datasource.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDataSource implements MemberRepository {
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MemberDataSource(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void registerMember(Member member) {
        memberMapper.insertMember(member);
    }

    @Override
    public boolean exists(MemberNumber memberNumber) {
        return memberMapper.exists(memberNumber);
    }
    @Override
    public Member findMember(MemberNumber memberNumber) {
        return memberMapper.selectMember(memberNumber);
    }
}
