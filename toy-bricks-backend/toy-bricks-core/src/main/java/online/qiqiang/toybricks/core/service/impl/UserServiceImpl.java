package online.qiqiang.toybricks.core.service.impl;

import lombok.RequiredArgsConstructor;
import online.qiqiang.toybricks.core.service.UserService;
import online.qiqiang.toybricks.core.vo.UserVO;
import online.qiqiang.toybricks.core.vo.helper.PageHelper;
import online.qiqiang.toybricks.core.vo.helper.UserVoHelper;
import online.qiqiang.toybricks.core.vo.request.UserPageRequest;
import online.qiqiang.toybricks.dal.entity.UserEntity;
import online.qiqiang.toybricks.dal.repository.UserRepository;
import online.qiqiang.toybricks.framework.common.vo.PageResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author quince
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<PageResult<UserVO>> list(UserPageRequest request) {
        UserEntity queryEntity = new UserEntity();
        queryEntity.setUsername(request.getUsername());
        queryEntity.setAge(request.getAge());
        queryEntity.setGender(request.getGender());
        Example<UserEntity> example = Example.of(
                queryEntity,
                // 所有条件都需要，简单来说大概就是条件通过 AND 连接
                ExampleMatcher.matchingAll()
                        // 如果属性为null，忽略它
                        .withIgnoreNullValues()
                        // 对于 name 属性，使用 contains 策略，简单来说就是模糊查询
                        .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains()));
        PageRequest pageRequest = PageRequest.of(request.getPageNum() - 1, request.getPageSize());

        return userRepository.findBy(example, query -> query.page(pageRequest))
                .as(pageMono -> PageHelper.pageTransformer(pageMono, UserVoHelper::entityToVo));
    }

    @Override
    public Mono<Long> batchCreateUser() {
        Flux<UserEntity> users = Flux.range(0, 1000)
                .flatMap(i -> Mono.just(UserVoHelper.createUser(i)));
        return userRepository.saveAll(users).count();
    }
}
