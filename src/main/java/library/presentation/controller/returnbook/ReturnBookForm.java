package library.presentation.controller.returnbook;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.ReturnDate;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class ReturnBookForm {

    @Valid MemberNumber memberNumber = null;

    @Valid BookCollectionCode bookCollectionCode = null;

    @Valid ReturnDate returnDate = new ReturnDate(Date.now());
}
