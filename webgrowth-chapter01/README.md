# 简单的web应用

## 一、一个简单的java web项目搭建

1. 在idea中新建好项目，然后在pom.xml中配置编码环境，JDK版本
2. 可以直接创建java web项目，也可以从普通项目转来；
3. 从普通转来需要在main目录下新建webapp/WEB-INF/web.xml，然后idea会Framework Detected，点击config，后点击OK；
4. 在web.xml文件中配置Servlet3.0相关描述，因为3.0，可以不用配置url-mapping，可通过注解使用；
5. 修改pom文件中打包方式为war；
6. 添加java web项目的依赖，servlet/jsp/jstl等；
7. 引入tomcat或者jetty等web容器；

## 二、编写一个简单的web应用

1. 编写Servlet类；
2. 编写jsp；

## 三、让web项目跑起来

### 3.1、可以在idea中配置tomcat运行

配置Run/Debug Configurations：
![](http://p5mck73dl.bkt.clouddn.com/web-growth01.png)

接着配置deployment：
![](http://p5mck73dl.bkt.clouddn.com/web-growth02.png)

然后就可以运行，通过浏览器访问就可以了，因为配置了on update action为redeploy，修改servlet后直接ctrl+F9就可以直接在页面刷新看到效果。

### 3.2、也可以用tomcat的maven插件

在pom.xml中配置插件：

```$xslt
<build>
        <plugins>
            <!-- Tomcat 以maven插件的形式引入tomcat容器-->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <path>/${project.artifactId}</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
![](http://p5mck73dl.bkt.clouddn.com/web-growth03.png)

点击tomcat7:run运行，修改jsp刷新可立即生效，但修改HelloServlet并不能实现热部署。

也可以在Run/Debug Configuration中配置maven启动，如下：

![](http://p5mck73dl.bkt.clouddn.com/web-growth04.png)

这时ctrl+F9可以实现热部署。

## 四、代码托管到git仓库

### 4.1、编写.gitignore忽略一些配置相关的文件

### 4.2、提交本地git仓库

### 4.3、推送到远程仓库
