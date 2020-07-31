package library.application.repository;

import library.domain.model.item.蔵書番号;
import library.domain.model.loan.Loan;
import library.domain.model.loan.rule.貸出状況;
import library.domain.model.loan.貸出依頼;
import library.domain.model.returned.返却;
import library.domain.model.member.会員番号;

/**
 * 貸出リポジトリ
 */
public interface 貸出リポジトリ {

    void loan(貸出依頼 貸出依頼);

    貸出状況 status(会員番号 会員番号);

    Loan findBy(蔵書番号 蔵書番号);

    void 返却完了(返却 返却);
}
