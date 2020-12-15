
package library.application.repository;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedList;
import library.domain.model.reservation.retention.Retention;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void retained(Retention retention);

    void loan(ItemNumber itemNumber);

    void expire(ItemNumber itemNumber);

    RetainedList retentions();

    Retained findBy(ItemNumber itemNumber);
}