package library.domain.model.bookonloan;

import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DelayPeriodTest {

    @Test
    void 遅延日数の状態を生成することができる() {
        LoanPeriod loanPeriod1 = new LoanPeriod(Date.from("2020-10-28"));
        LoanPeriod loanPeriod2 = new LoanPeriod(Date.from("2020-10-27"));
        LoanPeriod loanPeriod3 = new LoanPeriod(Date.from("2020-10-26"));
        LoanPeriod loanPeriod4 = new LoanPeriod(Date.from("2020-10-23"));
        LoanPeriod loanPeriod5 = new LoanPeriod(Date.from("2020-10-22"));

        DelayStatus delayPeriod1 = DelayStatus.from(loanPeriod1);
        DelayStatus delayPeriod2 = DelayStatus.from(loanPeriod2);
        DelayStatus delayPeriod3 = DelayStatus.from(loanPeriod3);
        DelayStatus delayPeriod4 = DelayStatus.from(loanPeriod4);
        DelayStatus delayPeriod5 = DelayStatus.from(loanPeriod5);

        assertEquals(delayPeriod1, DelayStatus.遅延日数３日未満);
        assertEquals(delayPeriod2, DelayStatus.遅延日数３日未満);
        assertEquals(delayPeriod3, DelayStatus.遅延日数７日未満);
        assertEquals(delayPeriod4, DelayStatus.遅延日数７日未満);
        assertEquals(delayPeriod5, DelayStatus.それ以外);
    }
}
