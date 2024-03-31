package online.qiqiang.toybricks.framework.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import online.qiqiang.toybricks.framework.common.vo.PairVO;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author quince
 */
public class CopyUtil {

    private static final Map<PairVO<Class, Class>, MapperFacade> map = new HashMap<>();
    static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();


    // public static <T, S> T copy(S source, Class<T> targetClass) {
    //     Class<?> sourceClass = source.getClass();
    //     PairVO<Class, Class> classPairVO = new PairVO<>(sourceClass, targetClass);
    //     MapperFacade mapperFacade = map.computeIfAbsent(classPairVO, key -> {
    //         ClassMapBuilder<?, T> classMapBuilder = MAPPER_FACTORY.classMap(sourceClass, targetClass);
    //         Set<PairVO<String, Class<?>>> sourceFields = getFields(sourceClass);
    //         Set<PairVO<String, Class<?>>> targetFields = getFields(targetClass);
    //         sourceFields.stream()
    //                 .filter(targetFields::contains)
    //                 .forEach(p -> classMapBuilder.field(p.getLeft(), p.getLeft()));
    //         classMapBuilder.byDefault()
    //                 .register();
    //         return MAPPER_FACTORY.getMapperFacade();
    //     });
    //     return mapperFacade.map(source, targetClass);
    // }
    public static <T, S> T copy(S source, Class<T> targetClass) {
        return BeanUtil.copyProperties(source, targetClass);
    }


    /**
     * 找到这个类所有的属性，这些属性必须存在 get 和 set 方法
     *
     * @param clazz class
     * @return
     */
    private static Set<PairVO<String, Class<?>>> getFields(Class<?> clazz) {
        Set<PairVO> fieldSet = Arrays.stream(ReflectUtil.getFields(clazz))
                .filter(f -> !Modifier.isFinal(f.getModifiers())
                        && !Modifier.isStatic(f.getModifiers())
                        && !f.isSynthetic()
                )
                .map(field -> new PairVO<>(field.getName(), field.getType()))
                .collect(Collectors.toSet());
        Set<PairVO<String, Class<?>>> sets = new HashSet<>();
        Set<PairVO<String, Class<?>>> gets = new HashSet<>();
        for (Method method : ReflectUtil.getMethods(clazz)) {
            if (method.getName().startsWith("get")) {
                String fieldName = StrUtil.subAfter(method.getName(), "get", false);
                if (StrUtil.isNotBlank(fieldName)) {
                    gets.add(new PairVO<>(StrUtil.lowerFirst(fieldName), method.getReturnType()));
                }
            }
            if (method.getName().startsWith("set")) {
                String fieldName = StrUtil.subAfter(method.getName(), "set", false);
                if (StrUtil.isNotBlank(fieldName)) {
                    sets.add(new PairVO<>(StrUtil.lowerFirst(fieldName), method.getParameterTypes()[0]));
                }
            }
        }
        Set<PairVO<String, Class<?>>> finalSet = new HashSet<>(fieldSet.size());
        for (PairVO pairVO : fieldSet) {
            if (sets.contains(pairVO) && gets.contains(pairVO)) {
                finalSet.add(pairVO);
            }
        }
        return finalSet;
    }
}
