package library.domain.model.reservation.retention;

import java.util.List;

/**
 * 準備完了の一覧
 */
public class 準備完了の一覧 {
    List<準備完了> list;

    public 準備完了の一覧(List<準備完了> list) {
        this.list = list;
    }

    public static 準備完了の一覧 empty() {
        return new 準備完了の一覧(List.of());
    }

    public List<準備完了> asList() {
        return list;
    }

    public String showCount() {
        if (list.size() == 0) return "取置中の蔵書はありません";
        return list.size() + "件を取り置いています";
    }

    @Override
    public String toString() {
        return "Retentions{" +
                "list=" + list +
                '}';
    }
}
