# 为web应用添加简单业务

## 一、需求分析与系统设计

### 1.1、需求分析

![](http://p0zk0k5xl.bkt.clouddn.com/web-growth05.png)

### 1.2、系统设计

1. 设计用例
* 查询客户
* 显示客户列表
* 客户基本信息
* 创建客户
* 编辑客户
* 删除客户

2. 设计表结构

customer:

![](http://p0zk0k5xl.bkt.clouddn.com/web-growth06.png)

3. 设计界面原型

4. 设计URL

并不是经典的RESTFUL风格的URL：

![](http://p0zk0k5xl.bkt.clouddn.com/web-growth07.png)

## 二、动手开发web应用

### 2.1、创建数据库

可以选择MySQL客户端工具Navicat帮助开发

### 2.2、准备开发环境

1. 参考chapter01新建搭建web项目
2. 采用MVC架构搭建Web应用项目结构(参考：[MVC入门经典——深入理解原理](http://blog.csdn.net/a1036645146/article/details/51493959))
![](http://p0zk0k5xl.bkt.clouddn.com/web-growth08.png)
3. 编写模型层
4. 编写控制层
5. 编写服务层
标准的MVC架构中，没有服务层，将该层作为衔接控制器层和数据库之间的桥梁，可以使用接口和类，在简单情况下可以
只使用类。
6. 编写单元测试
7. 编写视图层

### 2.3、完善细节与代码优化

前面只是搭建了一个框架，没有真正的业务逻辑，都是待实现的todo。

1. 完善服务层
 
* 添加日志
* 添加一些工具类
* 数据库连接




