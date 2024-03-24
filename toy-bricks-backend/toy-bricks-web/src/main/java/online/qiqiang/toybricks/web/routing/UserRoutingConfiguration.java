package online.qiqiang.toybricks.web.routing;


import online.qiqiang.toybricks.web.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author quince
 */
@Configuration(proxyBeanMethods = false)
public class UserRoutingConfiguration {

    private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route()
                .POST("/user/list", ACCEPT_JSON, userHandler::userPage)
                .GET("/user/batchCreateUser", ACCEPT_JSON, userHandler::batchCreateUser)
                .build();
    }

}
