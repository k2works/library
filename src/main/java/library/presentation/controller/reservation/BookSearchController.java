package library.presentation.controller.reservation;

import library.application.service.reservation.BookQueryService;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.book.Books;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 本検索
 */
@Controller
@RequestMapping("reservation/books")
public class BookSearchController {
    BookQueryService bookQueryService;

    public BookSearchController(BookQueryService bookQueryService) {
        this.bookQueryService = bookQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("searchKeyword", new BookSearchKeyword(""));
        return "reservation/books/list";
    }

    @GetMapping("search")
    String search(Model model, @ModelAttribute("searchKeyword") BookSearchKeyword searchKeyword, BindingResult result) {
        if (searchKeyword.isNull() || searchKeyword.isBlank()) return "reservation/books/list";

        Books books = bookQueryService.search(searchKeyword);

        model.addAttribute("books", books);
        model.addAttribute("searchKeyword", searchKeyword);
        return "reservation/books/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "searchKeyword.value"
        );
    }
}
