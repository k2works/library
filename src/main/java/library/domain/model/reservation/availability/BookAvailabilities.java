package library.domain.model.reservation.availability;

import library.domain.model.item.bibliography.NumberOfBook;

import java.util.List;

public class BookAvailabilities {
    List<BookAvailability> list;

    public BookAvailabilities(List<BookAvailability> list) {
        this.list = list;
    }

    public NumberOfBook numberOfBook() {
        return new NumberOfBook(list.size());
    }
    public int size() {
        return list.size();
    }

    public List<BookAvailability> asList() {
        return list;
    }
}
