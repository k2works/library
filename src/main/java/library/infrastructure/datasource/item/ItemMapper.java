package library.infrastructure.datasource.item;

import library.domain.model.item.蔵書;
import library.domain.model.item.蔵書番号;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    boolean exists(蔵書番号 蔵書番号);
    boolean loanable(蔵書番号 蔵書番号);
    boolean retained(蔵書番号 蔵書番号);
    boolean loaned(蔵書番号 蔵書番号);

    蔵書 selectItem(蔵書番号 蔵書番号);

    void insert貸出可能(蔵書番号 蔵書番号);
    void delete貸出可能(蔵書番号 蔵書番号);

    void insert貸出中(蔵書番号 蔵書番号);
    void delete貸出中(蔵書番号 蔵書番号);

    void insert取置中(蔵書番号 蔵書番号);
    void delete取置中(蔵書番号 蔵書番号);
}
