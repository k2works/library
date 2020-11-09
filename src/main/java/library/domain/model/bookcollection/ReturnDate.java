package library.domain.model.bookcollection;

import library.domain.type.date.Date;

/**
 * 返却日
 */
public class ReturnDate {
    Date value;

    @Override
    public String toString() {
        return value.toString();
    }
}
