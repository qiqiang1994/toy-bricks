package online.qiqiang.toybricks.web.handler;

import lombok.RequiredArgsConstructor;
import online.qiqiang.toybricks.core.service.UserService;
import online.qiqiang.toybricks.core.vo.request.UserChangeRequest;
import online.qiqiang.toybricks.core.vo.request.UserEditRequest;
import online.qiqiang.toybricks.core.vo.request.UserPageRequest;
import online.qiqiang.toybricks.framework.common.vo.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author quince
 */
@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> userPage(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(serverRequest.bodyToMono(UserPageRequest.class)
                                .flatMap(userService::list)
                                .flatMap(data -> Mono.just(Response.success(data)))
                        , Response.class
                );
    }

    public Mono<ServerResponse> batchCreateUser() {
        return ServerResponse.ok().body(userService.batchCreateUser(), Long.class);
    }

    public Mono<ServerResponse> change(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(serverRequest.bodyToMono(UserChangeRequest.class)
                                .flatMap(userService::change)
                                .flatMap(userVO -> Mono.just(Response.success()))
                        , Response.class);
    }

    public Mono<ServerResponse> edit(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(serverRequest.bodyToMono(UserEditRequest.class)
                                .flatMap(userService::edit)
                                .flatMap(userVO -> Mono.just(Response.success()))
                        , Response.class);
    }
}
