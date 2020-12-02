package library.domain.model.retention;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.reservation.ReservedBook;
import library.domain.type.date.Date;

/**
 * 取引済み蔵書
 */
public class RetainedBookCollection {
    ReservedBook reservedBook;
    RetainedDate retainedDate;
    BookCollection bookCollection;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }

    public boolean isExpired() {
        Date tody = Date.now();
        return retainedDate.value.isBefore(tody);
    }

    public boolean isA(BookCollection bookCollection) {
        return bookCollection.bookCollectionCode().sameValue(this.bookCollection.bookCollectionCode());
    }
}
