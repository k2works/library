package library.domain.model.item.bibliography;

/**
 * 本
 */
public class Book {
    BookNumber bookNumber;
    Title title;
    Author author;

    @Deprecated
    Book() {
    }

    public Book(BookNumber bookNumber, Title title, Author author) {
        this.bookNumber = bookNumber;
        this.title = title;
        this.author = author;
    }

    public BookNumber bookNumber() {
        return bookNumber;
    }

    public Title title() {
        return title;
    }

    public String show() {
        return String.format("%s (%s)", title, author);
    }
    public boolean isSameBook(Book other) {
        return bookNumber().sameValue(other.bookNumber);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNumber=" + bookNumber +
                ", title=" + title +
                ", author=" + author +
                '}';
    }
}