package library.domain.model.bookcollection;

import library.domain.model.book.Book;

/**
 * 蔵書
 */
public class BookCollection {
    BookCollectionCode bookCollectionCode;
    Book book;
    BookCollectionStatus bookCollectionStatus;
    WebReservationStatus webReservationStatus;
}
