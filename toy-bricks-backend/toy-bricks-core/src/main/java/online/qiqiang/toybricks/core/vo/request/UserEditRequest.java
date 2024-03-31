package online.qiqiang.toybricks.core.vo.request;

import lombok.Data;

/**
 * @author quince
 */
@Data
public class UserEditRequest {
    private Long id;
    private String username;
    private Integer gender;
    private String idCard;
    private String email;
    private String address;
    private String createTime;
    private Integer status;
    private String avatar;
    private User user;

    @Data
    public static class User {
        private Detail detail;

        @Data
        public static class Detail {
            private Integer age;
        }
    }
}
