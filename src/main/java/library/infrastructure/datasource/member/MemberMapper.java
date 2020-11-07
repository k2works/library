package library.infrastructure.datasource.member;

import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member selectMember(@Param("memberNumber") MemberNumber memberNumber);

    void insertMember(Member member);
}
