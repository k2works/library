package library.domain.model.counter;

import library.domain.model.bookonloan.librarycard.LibraryCardShelf;
import library.domain.model.item.Catalog;
import library.domain.model.item.Item;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.retention.RetentionShelf;
import library.domain.model.retention.RetentionableReservations;

import java.util.ArrayList;
import java.util.List;

/**
 * カウンター
 */
public class Counter {
    Catalog catalog;
    LibraryCardShelf libraryCardShelf;
    RetentionShelf retentionShelf;

    public Counter(Catalog catalog, LibraryCardShelf libraryCardShelf, RetentionShelf retentionShelf) {
        this.catalog = catalog;
        this.libraryCardShelf = libraryCardShelf;
        this.retentionShelf = retentionShelf;
    }

    public static Counter empty() {
        return new Counter(Catalog.empty(), LibraryCardShelf.empty(), RetentionShelf.empty());
    }

    public RetentionableReservations retentionableReservedBooks(Reservations reservations) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservations.asList()) {
            if (stockStatus(reservation.reservedBook()) == StockStatus.在庫あり) {
                list.add(reservation);
            }
        }

        return new RetentionableReservations(list);
    }

    private StockStatus stockStatus(ReservedBook reservedBook) {
        Catalog sameBookHoldings = catalog.findHoldingsByBook(reservedBook.book());

        for (Item item : sameBookHoldings.list()) {
            if (libraryCardShelf.findLibraryCard(item).isStocked() && retentionShelf.notContains(item)) {
                return StockStatus.在庫あり;
            }
        }

        return StockStatus.在庫なし;
    }
}
