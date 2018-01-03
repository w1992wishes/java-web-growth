package me.w1992wishes.smart.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 *
 * Created by w1992wishes
 * on 2017/12/22.
 */
public class Handler {

    //controller类
    private Class<?> controllerClass;
    //action方法
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
