package me.w1992wishes.smart.plugin.security.aspect;

import me.w1992wishes.smart.framework.annotation.Aspect;
import me.w1992wishes.smart.framework.annotation.Controller;
import me.w1992wishes.smart.framework.proxy.AspectProxy;
import me.w1992wishes.smart.plugin.security.annotation.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 授权注解切面
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
@Aspect(Controller.class)
public class AuthzAnnotationAspect extends AspectProxy {

    private static final Class[] ANNOTATION_CLASS_ARRAY = {
            User.class
    };

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        Annotation annotation = getAnnotation(cls, method);
        if (annotation != null){
            Class<?> annotationType = annotation.annotationType();
            if (annotationType.equals(User.class)){
                handleUser();
            }
        }
    }

    private Annotation getAnnotation(Class<?> cls, Method method){
        for (Class<? extends Annotation> annotationClass : ANNOTATION_CLASS_ARRAY){
            //先判断方法上是否带有授权注解
            if (method.isAnnotationPresent(annotationClass)) {
                return method.getAnnotation(annotationClass);
            }
            //后判断类上是否带有授权注解
            if (cls.isAnnotationPresent(annotationClass)) {
                return cls.getAnnotation(annotationClass);
            }
        }
        return null;
    }

    private void handleUser(){
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if (principals == null || principals.isEmpty()){
            throw new AuthenticationException("current user had not login");
        }
    }
}
