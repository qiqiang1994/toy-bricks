package online.qiqiang.toybricks.dal.repository;

import online.qiqiang.toybricks.dal.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * @author quince
 */
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {

}
