package library.infrastructure.datasource.holding;

import library.domain.model.book.BookId;
import library.domain.model.holding.Holding;
import library.domain.model.holding.HoldingCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HoldingMapper {
    Holding selectHolding(@Param("holdingCode") HoldingCode holdingCode);

    List<Holding> selectHoldings(@Param("holdingCodes") List<HoldingCode> holdingCodes);

    List<Holding> selectInStockHoldingsByBookIds(@Param("bookIds") List<BookId> bookIds);

    HoldingCode lockHolding(@Param("holdingCode") HoldingCode holdingCode);
}
