package library.application.repository;

import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.貸出予約一覧;

/**
 * 予約リポジトリ
 */
public interface 予約リポジトリ {
    void 予約する(貸出予約 tryingToReserveBook);

    貸出予約一覧 貸出予約一覧();

    貸出予約 findBy(予約番号 予約番号);

    void 取消す(貸出予約 貸出予約);
}
