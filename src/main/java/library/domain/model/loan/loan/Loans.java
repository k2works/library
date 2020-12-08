package library.domain.model.loan.loan;

import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 貸出図書リスト
 */
public class Loans {
    List<Loan> list;

    public Loans(List<Loan> list) {
        this.list = list;
    }

    public DelayPeriod worstDelayPeriod(Date date) {
        return list.stream()
                .map(loan -> loan.delayPeriod(date))
                .max(Comparator.comparingInt(period -> period.value.value()))
                .orElse(new DelayPeriod(new Days(0)));
    }

    public NumberOfBookOnLoans numberOfBookOnLoans() {
        return new NumberOfBookOnLoans(list.size());
    }

    public List<Loan> asList() {
        return Collections.unmodifiableList(list);
    }
}