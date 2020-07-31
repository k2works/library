package library.presentation.loan;

import library.application.coordinator.貸出業務.貸出業務;
import library.domain.model.loan.貸出依頼;
import library.domain.model.loan.rule.貸出状況;
import library.domain.model.loan.rule.貸出可否;
import library.domain.model.member.会員登録の状態;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static library.domain.model.loan.rule.貸出可否.貸出不可;
import static library.domain.model.member.会員登録の状態.未登録;

/**
 * 貸出の登録画面
 */
@Controller
@RequestMapping("loan/register")
public class LoanRegisterController {
    貸出業務 coordinator;

    public LoanRegisterController(貸出業務 coordinator) {
        this.coordinator = coordinator;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loanRequest", 貸出依頼.empty());
        return "loan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loanRequest") 貸出依頼 貸出依頼, BindingResult bindingResult,
                    RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return "loan/register/form";

        会員登録の状態 会員登録の状態 = coordinator.会員番号の有効性を確認する(貸出依頼);
        if ( 会員登録の状態 == 未登録) {
            bindingResult.addError(
                    new FieldError(bindingResult.getObjectName(),
                    "memberNumber.value", "この番号の会員はいません"));
            return "loan/register/form";
        }

        貸出可否 貸出可否 = coordinator.貸出制限を判断する(貸出依頼);
        if (貸出可否 == 貸出不可) {
            bindingResult.addError(new ObjectError("error", 貸出可否.message()));
            return "loan/register/form";
        }

        coordinator.貸し出す(貸出依頼);
        貸出状況 貸出状況 = coordinator.貸出状況を提示する(貸出依頼);

        attributes.addFlashAttribute("status", 貸出状況);
        return "redirect:/loan/register/completed";
    }

    @GetMapping("completed")
    String completed(@ModelAttribute("status") 貸出状況 status, Model model) {
        model.addAttribute("loanStatus", status);
        return "loan/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "memberNumber.value",
                "itemNumber.value",
                "loanDate.value"
        );
    }
}
