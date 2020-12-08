package library.infrastructure.datasource.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.reservation.retention.RetainedHolding;
import org.springframework.stereotype.Repository;

@Repository
public class RetentionDatasource implements RetentionRepository {
    RetentionMapper retentionMapper;

    public RetentionDatasource(RetentionMapper retentionMapper) {
        this.retentionMapper = retentionMapper;
    }

    @Override
    public void registerRetention(RetainedHolding retainedHolding) {
        retentionMapper.insertRetainedHolding(
            retainedHolding.reservedBook().reservationId(),
            retainedHolding.holding().itemNumber(),
            retainedHolding.retainedDate());
    }
}
