package library.application.repository;

import library.domain.model.member.会員;
import library.domain.model.member.会員番号;
import library.domain.model.member.会員登録の状態;

/**
 * 会員リポジトリ
 */
public interface 会員リポジトリ {
    会員登録の状態 status(会員番号 会員番号);
    会員 findBy(会員番号 会員番号);
}
