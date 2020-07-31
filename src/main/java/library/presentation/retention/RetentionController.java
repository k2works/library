package library.presentation.retention;

import library.application.coordinator.取置業務.取置業務;
import library.domain.model.item.蔵書番号;
import library.domain.model.item.蔵書の状態;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.retention.BookMatching;
import library.domain.model.reservation.retention.準備完了の一覧;
import library.domain.model.reservation.retention.取置依頼;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static library.domain.model.item.蔵書の状態.未登録;
import static library.domain.model.item.蔵書の状態.貸出可能;
import static library.domain.model.reservation.retention.BookMatching.不一致;

/**
 * 取置の管理画面
 */
@Controller("取置の管理")
@RequestMapping("retentions")
public class RetentionController {
    取置業務 取置業務;

    public RetentionController(取置業務 取置業務) {
        this.取置業務 = 取置業務;
    }

    @GetMapping
    String retainedList(Model model) {
        準備完了の一覧 準備完了の一覧 = 取置業務.準備完了を一覧する();
        model.addAttribute("retainedList", 準備完了の一覧);
        return "retention/retentions";
    }

    @PostMapping
    String retain(@Validated @ModelAttribute("retention") 取置依頼 取置依頼, BindingResult bindingResult,
                  Model model) {

        貸出予約 貸出予約 = 取置業務.予約を見つける(取置依頼.reservationNumber());
        model.addAttribute("reservation", 貸出予約);

        if (bindingResult.hasErrors()) {
            return "retention/form";
        }

        蔵書の状態 蔵書の状態 = 取置業務.蔵書の状態を確認する(取置依頼.蔵書番号());

        if (蔵書の状態 == 未登録) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", 蔵書の状態.description()));
            return "retention/form";
        }

        BookMatching matching = 取置業務.予約された本であることを確認する(貸出予約, 取置依頼);
        if (matching == 不一致) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", matching.description()));
            return "retention/form";
        }

        if (蔵書の状態 != 貸出可能) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", 蔵書の状態.description()));
            return "retention/form";
        }

        取置業務.取り置く(取置依頼);

        return "redirect:/retentions/requests";
    }

    @PostMapping(value = "loans", params = {"loaned"})
    String loan(@RequestParam("loaned") 蔵書番号 蔵書番号) {
        取置業務.貸し出す(蔵書番号);
        return "redirect:/retentions";
    }

    @PostMapping(value = "loans", params = {"expired"})
    String expired(@RequestParam("expired") 蔵書番号 蔵書番号) {
        取置業務.取置の期限切れ(蔵書番号);
        return "redirect:/retentions";
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "reservationNumber.value"
        );
    }
}