package me.w1992wishes.smart.framework.proxy;

/**
 * 代理接口
 *
 * Created by w1992wishes
 * on 2017/12/27.
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
