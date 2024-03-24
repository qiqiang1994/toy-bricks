package online.qiqiang.toybricks.core.vo.request;

import lombok.Getter;
import lombok.Setter;
import online.qiqiang.toybricks.framework.common.vo.PageRequest;

/**
 * @author quince
 */
@Getter
@Setter
public class UserPageRequest extends PageRequest {
    private String username;
    private Integer gender;
    private Integer age;

}
