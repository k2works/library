package library.domain.model.reservation;

import library.domain.model.bookcollection.SameBooksBookCollections;
import library.domain.model.member.Member;

/**
 * 貸出予約
 */
public class Reservation {
    Member member;
    SameBooksBookCollections sameBooksCollections;
    RetentionStatus retentionStatus;

    @Deprecated
    Reservation() {
    }

    public Reservation(Member member, SameBooksBookCollections sameBooksCollections, RetentionStatus retentionStatus) {
        this.member = member;
        this.sameBooksCollections = sameBooksCollections;
        this.retentionStatus = retentionStatus;
    }

    public Member member() {
        return member;
    }

    public SameBooksBookCollections sameBooksCollections() {
        return sameBooksCollections;
    }

    public RetentionStatus retentionStatus() {
        return retentionStatus;
    }
}
