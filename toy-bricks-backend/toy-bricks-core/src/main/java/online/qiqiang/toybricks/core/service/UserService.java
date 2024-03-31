package online.qiqiang.toybricks.core.service;

import online.qiqiang.toybricks.core.vo.UserVO;
import online.qiqiang.toybricks.core.vo.request.UserChangeRequest;
import online.qiqiang.toybricks.core.vo.request.UserEditRequest;
import online.qiqiang.toybricks.core.vo.request.UserPageRequest;
import online.qiqiang.toybricks.framework.common.vo.PageResult;
import reactor.core.publisher.Mono;

/**
 * @author quince
 */
public interface UserService {
    Mono<PageResult<UserVO>> list(UserPageRequest request);

    Mono<UserVO> change(UserChangeRequest request);
    Mono<UserVO> edit(UserEditRequest request);

    Mono<Long> batchCreateUser();
}
