package library.infrastructure.datasource.reservation;

import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    予約番号 nextNumber();

    void insertReservation(
            @Param("reservationNumber") 予約番号 予約番号,
            @Param("memberNumber") 会員番号 会員番号,
            @Param("bookNumber") 書籍番号 書籍番号);

    List<貸出予約> selectAllReservation();

    貸出予約 selectReservation(予約番号 予約番号);

    void cancelReservation(予約番号 予約番号);
}
