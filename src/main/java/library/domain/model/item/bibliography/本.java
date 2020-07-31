package library.domain.model.item.bibliography;

/**
 * 本
 */
public class 本 {
    書籍番号 書籍番号;
    Title title;
    Author author;

    @Deprecated
    本() {
    }

    public 本(書籍番号 書籍番号, Title title, Author author) {
        this.書籍番号 = 書籍番号;
        this.title = title;
        this.author = author;
    }

    public 書籍番号 bookNumber() {
        return 書籍番号;
    }

    public boolean isSame(本 other) {
        return 書籍番号.sameValue(other.書籍番号);
    }
    public Title title() {
        return title;
    }

    public String show() {
        return String.format("%s (%s)", title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNumber=" + 書籍番号 +
                ", title=" + title +
                ", author=" + author +
                '}';
    }
}
