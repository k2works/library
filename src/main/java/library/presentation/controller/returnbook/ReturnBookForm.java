package library.presentation.controller.returnbook;

import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.ReturnDate;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class ReturnBookForm {
    @Valid
    ItemNumber itemNumber = null;

    @Valid
    ReturnDate returnDate = new ReturnDate(Date.now());
}
