package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.member.Members;

/**
 * 会員レポジトリ
 */
public interface MemberRepository {
    void registerMember(Member member);

    Members findMembers();
}
