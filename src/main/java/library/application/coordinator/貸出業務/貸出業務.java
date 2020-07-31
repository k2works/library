package library.application.coordinator.貸出業務;

import library.application.service.会員.会員参照サービス;
import library.application.service.貸出.貸出参照サービス;
import library.application.service.貸出.貸出記録サービス;
import library.application.service.返却.返却登録サービス;
import library.domain.model.loan.rule.貸出可否;
import library.domain.model.loan.rule.貸出状況;
import library.domain.model.loan.貸出依頼;
import library.domain.model.member.会員登録の状態;
import org.springframework.stereotype.Service;

@Service
public class 貸出業務 {
    会員参照サービス 会員参照サービス;
    貸出参照サービス 貸出参照サービス;
    貸出記録サービス 貸出記録サービス;
    返却登録サービス 返却登録サービス;

    public 貸出業務(
            会員参照サービス 会員参照サービス,
            貸出参照サービス 貸出参照サービス,
            貸出記録サービス 貸出記録サービス,
            返却登録サービス 返却登録サービス) {
        this.会員参照サービス = 会員参照サービス;
        this.貸出参照サービス = 貸出参照サービス;
        this.貸出記録サービス = 貸出記録サービス;
        this.返却登録サービス = 返却登録サービス;
    }

    public 会員登録の状態 会員番号の有効性を確認する(貸出依頼 貸出依頼) {
        return 会員参照サービス.会員登録の状態を確認する(貸出依頼.会員番号());
    }

    public 貸出可否 貸出制限を判断する(貸出依頼 貸出依頼) {
        貸出状況 貸出状況 = 貸出参照サービス.会員の貸出を一覧する(貸出依頼.会員番号());
        return 貸出状況.shouldRestrict();
    }

    public void 貸し出す(貸出依頼 貸出依頼) {
        貸出記録サービス.貸出を記録する(貸出依頼);
    }

    public 貸出状況 貸出状況を提示する(貸出依頼 貸出依頼) {
        return 貸出参照サービス.会員の貸出を一覧する(貸出依頼.会員番号());
    }
}
