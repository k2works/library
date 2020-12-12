package library.application.repository;

import library.domain.model.reservation.retention.RetainedList;
import library.domain.model.reservation.retention.Retention;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(Retention retention);

    RetainedList retentions();
}
