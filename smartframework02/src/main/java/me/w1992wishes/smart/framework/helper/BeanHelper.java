package me.w1992wishes.smart.framework.helper;

import me.w1992wishes.smart.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 *
 * Created by w1992wishes
 * on 2017/12/20.
 */
public final class BeanHelper {

    //bean映射,用于存放bean类与bean实例的对应关系
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        beanClassSet.stream()
                .map(ReflectionUtil::newInstance)
                .forEach(bean -> BEAN_MAP.put(bean.getClass(), bean));
    }

    /**
     * 获取Bean映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean实例
     * @param cls
     * @param bean
     */
    public static void setBean(Class<?> cls, Object bean){
        BEAN_MAP.put(cls, bean);
    }
}
