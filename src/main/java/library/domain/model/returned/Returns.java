package library.domain.model.returned;

import java.util.List;

/**
 * 返却一覧
 */
public class Returns {
    List<返却> list;

    public Returns(List<返却> list) {
        this.list = list;
    }

    public int count() {
        return list.size();
    }
}
