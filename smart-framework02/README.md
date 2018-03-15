# 使web框架具有aop特性(smart-framework02)

## 一、引子

如果想统计每个方法的执行时间，怎么实现呢？

在每个方法运行前获取时间，运行后再获取时间，相减？这样可以，但每个方法都这样会不会很繁琐？

aop是更好的解决方法。

## 二、代理

了解aop之前，应该先了解一下代理，代理可以分为**静态代理**和**动态代理**。

静态代理需要大量的代理类，动态代理可以只有一个代理类。

于java而言，动态代理由可分为**JDK动态代理**和**CGlib动态代理**：
* JDK动态代理：只能代理有接口的类，不能代理没有接口的类
* CGlib动态代理：可以代理有接口的类，也可以代理没有接口的类

## 三、AOP

面向切面编程：

切面是AOP中的一个术语，表示从业务逻辑中分离出来的横切逻辑，如性能监控、日志记录、权限控制等。
这些功能可以从核心的业务逻辑代码中抽离。也就是说AOP可以减少耦合，职责单一。

### 3.1、Spring AOP

Spring AOP是利用动态代理开发的。

附上一篇博文：[Spring之AOP由浅入深](https://www.cnblogs.com/zhaozihan/p/5953063.html)

### 3.2、Spring + AspectJ

Spring 开发者老罗开发了spring aop，虽然一直改进，但还是很麻烦，在配置文件中存在大量的切面配置，解决方案是继承AspectJ

## 四、开发AOP框架

### 4.1、 首先在smart-framework01的基础上，定义切面注解Aspect

这个注解用在切面代理类上；

### 4.2、搭建代理框架

1. 定义一个代理接口Proxy，有一个待实现的doProxy方法，该方法接收一个ProxyChain参数；
2. 定义链式代理ProxyChain，维护目标类的所有代理类，并且链式调用目标类所有的代理；
3. 定义一个专职于创建代理对象的类ProxyManager；
4. 定义切面类AspectProxy；

### 4.3、加载AOP框架

定义AopHelper，先获取继承AspectProxy的类的集合（即所有的代理类），然后拿到代理类上面的Aspect注解，获取该代理类
代理的目标类，得到 “代理类-目标类集合” 映射关系，然后根据这个关系得到 “目标类-代理对象集合” 映射关系，然后根据
目标类-代理对象链得到最终的代理对象，放入“类-对象映射”map中。

需要注意的是：应在加载IocHelper之前AopHelper，这样注入的对象就是代理对象了。

![](http://p5mck73dl.bkt.clouddn.com/web-growth09.png)

![](http://p5mck73dl.bkt.clouddn.com/web-growth10.png)

## 五、ThreadLocal简介

ThreadLocal为每个线程提供一个独立的副本。

## 六、事务简介

[数据库事务与锁详解](http://blog.csdn.net/aluomaidi/article/details/52460844)

## 七、实现事务控制特性

利用先前的开发的aop特性完成事务控制。

### 7.1、定义事务注解Transaction

方法级别的注解

### 7.2、提供事务相关操作

在DataBaseHelper中新增开启事务，提交事务，回滚事务的方法。

### 7.3、编写事务代理切面类

TransactionProxy的doProxy方法中，会在目标方法执行前打开事务，执行后提交事务，失败回滚事务。

然后还需要将编写的事务代理机制添加到框架中，要在AopHelper中做一些改变。

![](http://p5mck73dl.bkt.clouddn.com/web-growth11.png)

![](http://p5mck73dl.bkt.clouddn.com/web-growth12.png)

## 八、测试

在测试时，有需要注意的是，应将DatabaseHelper中的closeConnection方法注释，否则在提交事务时，
获取的不是同一个connection，会报错。

![](http://p5mck73dl.bkt.clouddn.com/web-growth13.png)