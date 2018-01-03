package me.w1992wishes.smart.framework.cglib;

import org.junit.Test;

/**
 * Created by w1992wishes
 * on 2017/12/29.
 */
public class ProxyTest {

    @Test
    public void proxy(){
        CGLibDynamicProxy.getInstance().getProxy(PersonImpl.class).sayHello();
    }

}
