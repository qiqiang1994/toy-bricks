package online.qiqiang.toybricks.framework.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quince
 */
@Data
public class PageResult<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<T> list;

    public void addData(T item) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(item);
    }
}
