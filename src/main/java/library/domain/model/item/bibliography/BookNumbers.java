package library.domain.model.item.bibliography;

import java.util.List;

/**
 * 書籍番号のリスト
 */
public class BookNumbers {
    List<書籍番号> list;

    public BookNumbers(List<書籍番号> list) {
        this.list = list;
    }

    public List<書籍番号> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
