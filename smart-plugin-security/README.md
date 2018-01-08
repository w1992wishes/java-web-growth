# 实现安全框架控制 smart-plugin-security

## 一、Shiro

Shiro是一款轻量级Java安全框架，提供了认证服务，授权服务，会话管理，加密等服务。

## 二、如何实现安全框架

### 2.1、添加相关依赖

依赖servlet,jsp,shiro-web,smart-framework

### 2.2、初始化插件

Servlet容器启动时会自动加载web应用的web.xml文件与classpath中的类，使用
shiro框架，先在web.xml中添加：
```
    <listener>
        <listener-class>org.apache.shiro.web.env.EnvironmentLoaderListener</listener-class>
    </listener>

    <filter>
        <filter-name>ShiroFilter</filter-name>
        <filter-class>org.apache.shiro.web.servlet.ShiroFilter</filter-class>
    </filter>
    
    <filter-mapping>
        <filter-name>ShiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
通过EnvironmentLoaderListener读取classpath中的shiro.ini文件，并加载其中的相关配置到内存中，
一边ShiroFilter可随时读取，当客户端请求时，被ShiroFilter拦截，将请求中的URL和shiro.ini文件
中的相关配置进行比较。

我们的框架需要封装shiro相关细节，也包括配置文件，这需要通过Servlet3.0规范提供的注册特性来省略
web.xml的相关配置，并将shiro.ini改为smart-security.ini文件。

这些需要在web应用初始化的时候完成，可以实现ServletContainerInitializer接口，并在classpath下的
META-INF/services/javax.servlet.ServletContainerInitializer添加需要初始化的类实现。

暂且实现这样一个类：SmartSecurityPlugin。


