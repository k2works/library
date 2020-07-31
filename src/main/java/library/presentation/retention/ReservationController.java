package library.presentation.retention;

import library.application.coordinator.取置業務.取置業務;
import library.domain.model.reservation.request.予約番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.request.貸出予約一覧;
import library.domain.model.reservation.retention.取置依頼;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 予約の管理画面
 */
@Controller("予約の管理")
@RequestMapping("retentions/requests")
public class ReservationController {
    取置業務 取置業務;

    public ReservationController(取置業務 取置業務) {
        this.取置業務 = 取置業務;
    }

    @GetMapping
    String requests(Model model) {
        貸出予約一覧 貸出予約一覧 = 取置業務.未準備の予約を一覧する();
        model.addAttribute("reservations", 貸出予約一覧);
        return "retention/requests";
    }

    @GetMapping("{reservationNumber}")
    String retentionForm(
            @PathVariable("reservationNumber") 予約番号 予約番号,
            Model model) {
        貸出予約 貸出予約 = 取置業務.予約を見つける(予約番号);
        model.addAttribute("reservation", 貸出予約);
        model.addAttribute("retention", new 取置依頼());
        return "retention/form";
    }

    @PostMapping("canceled")
    String cancel(@RequestParam("notAvailable") 予約番号 予約番号){
        取置業務.予約の取り消し(予約番号);
        return "redirect:/retentions/requests";
    }
}
