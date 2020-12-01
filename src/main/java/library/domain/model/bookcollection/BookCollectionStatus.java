package library.domain.model.bookcollection;

/**
 * 蔵書の状態
 */
public enum BookCollectionStatus {
    在庫中,
    貸出中;

    public boolean unLoanable() {
        return this == 貸出中;
    }
}
