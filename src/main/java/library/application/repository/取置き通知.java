package library.application.repository;

import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.retention.準備完了;

public interface 取置き通知 {
    void retained(準備完了 準備完了);
    void notAvailable(貸出予約 貸出予約);
}
