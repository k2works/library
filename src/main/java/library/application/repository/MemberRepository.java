package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.Members;

/**
 * 会員レポジトリ
 */
public interface MemberRepository {
    void registerMember(Member member);

    Member findMember(MemberNumber memberNumber);

    Members findMembers();
}
