package library.presentation.returns;

import library.application.service.返却.返却登録サービス;
import library.domain.model.item.蔵書番号;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.返却;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 返却の登録画面
 */
@Controller
@RequestMapping("returns/register")
public class ReturnBookRegisterController {
    返却登録サービス 返却登録サービス;

    public ReturnBookRegisterController(返却登録サービス 返却登録サービス) {
        this.返却登録サービス = 返却登録サービス;
    }

    @GetMapping
    String init(Model model) {
        返却 返却 = new 返却(new 蔵書番号(""), ReturnDate.now());
        model.addAttribute("returned", 返却);
        return "returns/register/form";
    }

    @PostMapping
    String returned(@Validated @ModelAttribute("returned") 返却 返却,
                    BindingResult result) {
        if (result.hasErrors()) return "returns/register/form";

        返却登録サービス.返却を記録する(返却);

        return "redirect:/returns/register/completed";
    }

    @GetMapping("completed")
    String completed() {
        return "returns/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "returnDate.value"
        );
    }
}
