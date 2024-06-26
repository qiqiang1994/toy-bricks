package online.qiqiang.toybricks.core.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import online.qiqiang.toybricks.framework.servicejson.LongToStringSerializer;

import java.util.List;

/**
 * @author quince
 */
@Data
public class UserVO {
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    private String username;
    private Integer gender;
    private User user;
    private String idCard;
    private String email;
    private String address;
    private String createTime;
    private Integer status;
    private String avatar;
    private List<UserVO> children;

    @Data
    public static class User {
        private Detail detail;

        @Data
        public static class Detail {
            private Integer age;
        }
    }

}
