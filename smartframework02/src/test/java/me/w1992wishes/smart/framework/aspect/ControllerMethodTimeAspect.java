package me.w1992wishes.smart.framework.aspect;

import me.w1992wishes.smart.framework.annotation.Aspect;
import me.w1992wishes.smart.framework.annotation.Controller;
import me.w1992wishes.smart.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by w1992wishes
 * on 2017/12/29.
 */
@Aspect(Controller.class)
public class ControllerMethodTimeAspect extends AspectProxy {

    private long start;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{
        start = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        System.out.println("speed time: " + (System.currentTimeMillis() - start));
    }

}
