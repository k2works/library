package library.application.repository;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollections;

/**
 * 蔵書レポジトリ
 */
public interface BookCollectionRepository {
    void registerBookCollection(BookCollection bookCollection);

    BookCollections findBookCollections();
}
