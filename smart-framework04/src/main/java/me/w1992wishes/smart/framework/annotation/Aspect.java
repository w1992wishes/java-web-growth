package me.w1992wishes.smart.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by w1992wishes
 * on 2017/12/27.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     */
    Class<? extends Annotation> value();

}
