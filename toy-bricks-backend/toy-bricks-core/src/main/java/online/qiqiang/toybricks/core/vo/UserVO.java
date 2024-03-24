package online.qiqiang.toybricks.core.vo;

import lombok.Data;

import java.util.List;

/**
 * @author quince
 */
@Data
public class UserVO {
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
