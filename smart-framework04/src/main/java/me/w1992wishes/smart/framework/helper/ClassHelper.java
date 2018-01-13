package me.w1992wishes.smart.framework.helper;

import me.w1992wishes.smart.framework.annotation.Controller;
import me.w1992wishes.smart.framework.annotation.Service;
import me.w1992wishes.smart.framework.ds.InstanceFactory;
import me.w1992wishes.smart.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * 类操作助手类
 *
 * Created by w1992wishes
 * on 2017/12/20.
 */
public final class ClassHelper {

    //类集合，用于存放所加载的类
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包下所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包下所有service类
     *
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        return getClassSetByAnnotation(Service.class);
    }

    /**
     * 获取应用包下所有的controller类
     *
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        return getClassSetByAnnotation(Controller.class);
    }

    /**
     * 获取应用包下所有的bean类（Service和Controller类）
     *
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    /**
     * 获取应用包下指定注解的类
     *
     * @param clazz
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> clazz){
        return CLASS_SET.stream()
                 .filter(cls -> cls.isAnnotationPresent(clazz))
                 .collect(toSet());
    }

    /**
     * 获取某父类或接口的所有子类
     *
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        return CLASS_SET.stream()
                .filter(cls -> superClass.isAssignableFrom(cls) && !superClass.equals(cls))
                .collect(toSet());
    }

}
