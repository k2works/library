package library.infrastructure.datasource.member;

import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean exists(会員番号 会員番号);
    会員 selectMember(会員番号 会員番号);
}
