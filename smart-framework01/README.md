# 搭建轻量级java web框架(smart-framework)

## 一、确定目标

一个简单的轻量级MVC框架

## 二、搭建开发环境

创建一个普通maven项目smart-framework，然后添加依赖：
* Servlet，JSP，JSTL等依赖；
* Logger依赖；
* MySQL依赖，JDBC类库DbUtils依赖，数据库连接池dbcp2依赖；
* Apache Commons常用工具依赖；
* 还有JSON序列化工具，这里选择Jackson；

## 三、定义框架配置项

一个配置常量类ConfigConstant，规定了配置文件名必须为smart.properties

## 四、加载配置项

定义了一个配置文件加载助手类ConfigHelper，帮助获取配置文件中的属性值

## 五、开发一个类加载器

开发一个“类加载器”来加载基础包下的所有类，比如使用了某注解的类，实现了某接口的类，或者某个类的所有子类

* 首先先定义一个类操作工具类ClassUtil，可以用来帮助加载类;
* 期望在控制层使用Controller注解，在控制器方法上使用Action注解，在服务类上使用Service注解，在控制器中
可以用Inject注解将服务类注入，所以定义四个注解；
* 一个类操作助手类ClassHelper，用于加载所有的控制器类，所有的服务类等；

## 六、实现bean容器

* ClassHelper可以帮助加载指定的类，但不能实例化，所以开发一个实例化工具ReflectionUtil；
* 开发一个BeanHelper帮助获取相应类的实例；

## 七、实现依赖注入功能

简单实现IocHelper：先通过BeanHelper获取所有的BEAN_MAP（类和实例的映射），然后遍历这个映射，取出所有的类和实例，
进而通过反射获取类中的所有成员变量，接着遍历成员变量，判断是否有Inject注解，若有，获得类对应的实例，
通过ReflectionUtil#setField方法设置。

IocHelper只有一个static块，所以当IocHelper被加载时，静态代码块就会执行。

因为所有的实例都放入到BEAN_MAP中，然后都从这里获取，所以所有的bean都是单例。

## 八、加载Controller

通过ClassHelper可以获取所有的Controller，然后可以通过反射获取Controller中所有带Action注解的方法，获取Action
注解中的请求表达式，进而获取请求方法和请求路径，封装成Request对象与Handler对象(哪个Controller中的哪个方法)，最后建立Request和Handler的映射，
放入Map中，并提供一个可根据请求方法与请求路径获取处理对象的方法。

## 九、初始化框架

第一次访问类时，就会加载static块，新增HelperLoader只是让加载更集中

## 十、请求转发器

以上都是在为这部做准备；

1. 首先开发一个Servlet，它处理所有的请求；
2. 从HttpServletRequest中获取请求方法和路径，封装成Request，然后用ControllerHelper.getHandler获取Handler。
3. 同时可以获取请求参数，封装成Param；
4. 拿到Handler后，就可以获取处理请求的Controller类，并通过BeanHelper.getBean获取Controller实例；
5. 调用Handler的method方法，即Controller的方法，可以获取返回值，返回值有两种：
    * View型的视图对象，则返回一个JSP页面；
    * Data型的数据对象，则返回一个JSON数据。








