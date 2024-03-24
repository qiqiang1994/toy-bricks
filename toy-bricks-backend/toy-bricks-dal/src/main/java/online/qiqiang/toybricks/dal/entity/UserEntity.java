package online.qiqiang.toybricks.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author quince
 */
@Data
@Table("tb_user")
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private Integer gender;
    private String idCard;
    private String email;
    private String address;
    private LocalDateTime createTime;
    private Integer status;
    private String avatar;
    private Integer age;
}
