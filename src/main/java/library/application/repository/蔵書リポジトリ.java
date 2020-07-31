package library.application.repository;

import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書番号;
import library.domain.model.item.蔵書の状態;

/**
 * 蔵書リポジトリ
 */
public interface 蔵書リポジトリ {
    蔵書の状態 status(蔵書番号 蔵書番号);
    蔵書 findBy(蔵書番号 蔵書番号);
}
