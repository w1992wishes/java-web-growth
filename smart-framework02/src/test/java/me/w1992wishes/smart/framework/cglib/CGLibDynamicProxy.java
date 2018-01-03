package me.w1992wishes.smart.framework.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by w1992wishes
 * on 2017/12/29.
 */
public class CGLibDynamicProxy implements MethodInterceptor {

    private CGLibDynamicProxy(){}

    public static CGLibDynamicProxy getInstance(){
        return ProxyHolder.S_INSTANCE;
    }

    private static class ProxyHolder{
        private static final CGLibDynamicProxy S_INSTANCE = new CGLibDynamicProxy();
    }

    @SuppressWarnings("unchecked")
    public <T>  T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }
}
