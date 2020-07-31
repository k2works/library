package library.infrastructure.transfer;

import library.application.repository.取置き通知;
import library.domain.model.member.会員番号;
import library.domain.model.reservation.request.貸出予約;
import library.domain.model.reservation.retention.準備完了;
import org.springframework.stereotype.Component;

@Component
public class Simple取置き通知 implements 取置き通知 {
    @Override
    public void retained(準備完了 準備完了) {
        String message = String.format(
                "予約いただいた本が準備できました。\n本：%s\n取置期限:%s",
                準備完了.showBook(),
                準備完了.showExpireDate()
        );
        notify(準備完了.memberNumber(), message);
    }

    @Override
    public void notAvailable(貸出予約 貸出予約) {
        String message = String.format(
                "予約いただいた本は在庫がありませんでした。\n本：%s",
                貸出予約.showBook()
        );
        notify(貸出予約.memberNumber(), message);

    }

    private void notify(会員番号 会員番号, String message) {
        System.out.println("通知宛先：" + 会員番号);
        System.out.println(message);
    }
}
