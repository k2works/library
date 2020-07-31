package library.presentation.reservation;

import library.application.coordinator.予約受付業務.予約受付業務;
import library.domain.model.item.bibliography.キーワード;
import library.domain.model.reservation.availability.本の一覧と貸出可否;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本の検索画面
 */
@Controller
@RequestMapping("reservation/books")
public class BookSearchController {
    予約受付業務 予約受付業務;

    public BookSearchController(予約受付業務 予約受付業務) {
        this.予約受付業務 = 予約受付業務;
    }

    @GetMapping("search")
    String search(Model model, @ModelAttribute("searchKeyword") キーワード searchキーワード, BindingResult result) {
        本の一覧と貸出可否 availabilities = 予約受付業務.本を探す(searchキーワード);
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("searchKeyword", searchキーワード);
        return "reservation/books/search";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "searchKeyword.value"
        );
    }
}
