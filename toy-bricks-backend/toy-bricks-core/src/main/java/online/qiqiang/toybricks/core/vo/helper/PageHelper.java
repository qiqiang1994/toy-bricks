package online.qiqiang.toybricks.core.vo.helper;

import online.qiqiang.toybricks.framework.common.vo.PageResult;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @author quince
 */
public class PageHelper {
    public static <E, V> Mono<PageResult<V>> pageTransformer(Mono<Page<E>> pageMono, Function<E, V> function) {
        return pageMono.flatMap(p -> {
            PageResult<V> pageResult = new PageResult<>();
            pageResult.setTotal(p.getTotalElements());
            pageResult.setPageNum(p.getNumber() + 1);
            pageResult.setPageSize(p.getSize());
            pageResult.setList(p.stream().map(function).toList());
            return Mono.just(pageResult);
        });
    }
}
