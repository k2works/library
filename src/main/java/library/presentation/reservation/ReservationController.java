package library.presentation.reservation;

import library.application.coordinator.予約受付業務.予約受付業務;
import library.domain.model.item.bibliography.本;
import library.domain.model.item.bibliography.書籍番号;
import library.domain.model.member.会員番号;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static library.domain.model.member.会員登録の状態.未登録;

/**
 * 予約の登録画面
 */
@Controller
@RequestMapping("reservation/register")
public class ReservationController {
    予約受付業務 予約受付業務;

    public ReservationController(予約受付業務 予約受付業務) {
        this.予約受付業務 = 予約受付業務;
    }

    @GetMapping
    String redirectToSearch() {
        return "redirect:/reservation/books/search";
    }

    @GetMapping(params = {"book"})
    String reservationForm(@RequestParam("book") 書籍番号 書籍番号, Model model) {
        本 本 = 予約受付業務.本を見つける(書籍番号);
        model.addAttribute("book", 本);
        model.addAttribute("member", 会員番号.empty());
        return "reservation/register/form";
    }

    @PostMapping
    String register(
            @RequestParam("book") 書籍番号 書籍番号,
            @ModelAttribute("member") 会員番号 会員番号,
            BindingResult bindingResult,
            Model model
            ) {

        本 本 = 予約受付業務.本を見つける(書籍番号);
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", 本);
            return "reservation/register/form";
        }

        if (予約受付業務.会員番号の有効性を確認する(会員番号) == 未登録) {
            model.addAttribute("member", 会員番号);
            model.addAttribute("book", 本);
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),"value","その番号の会員はいません"));
            return "reservation/register/form";
        }

        予約受付業務.予約を記録する(本, 会員番号);

        return "redirect:/reservation/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model) {
        return "reservation/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "member.value"
        );
    }
}
