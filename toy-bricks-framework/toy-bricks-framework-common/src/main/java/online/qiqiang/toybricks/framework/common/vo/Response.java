package online.qiqiang.toybricks.framework.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author quince
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private T data;
    private String msg;

    public static <T> Response<T> success(T data) {
        return new Response<>(200, data, "成功");
    }
}
