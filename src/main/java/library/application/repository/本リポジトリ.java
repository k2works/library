package library.application.repository;

import library.domain.model.item.bibliography.キーワード;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.item.bibliography.本;
import library.domain.model.reservation.availability.本の一覧と貸出可否;

/**
 * 本リポジトリ
 */
public interface 本リポジトリ {

    本の一覧と貸出可否 探す(キーワード キーワード);

    本 見つける(書籍番号 書籍番号);
}
