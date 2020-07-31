package library.infrastructure.datasource.retention;

import library.domain.model.item.蔵書番号;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.retention.準備完了;
import library.domain.model.reservation.retention.RetainedDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RetentionMapper {

    void insert未準備(予約番号 予約番号);

    void delete未準備(予約番号 予約番号);

    void insert取置履歴(
            @Param("reservationNumber") 予約番号 予約番号,
            @Param("itemNumber") 蔵書番号 蔵書番号,
            @Param("retainedDate") RetainedDate retainedDate);

    void insert準備完了(
            @Param("reservationNumber") 予約番号 予約番号,
            @Param("itemNumber") 蔵書番号 蔵書番号,
            @Param("retainedDate") RetainedDate retainedDate);

    List<準備完了> selectAll準備完了();

    準備完了 select準備完了(蔵書番号 蔵書番号);
    void delete準備完了(蔵書番号 蔵書番号);

    void insert取置解放履歴(
            @Param("reservationNumber") 予約番号 予約番号,
            @Param("itemNumber") 蔵書番号 蔵書番号);

    void insert取置期限切れ履歴(予約番号 予約番号);
}
