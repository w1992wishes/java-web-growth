package me.w1992wishes.smart.framework.helper;

import me.w1992wishes.smart.framework.annotation.Action;
import me.w1992wishes.smart.framework.bean.Handler;
import me.w1992wishes.smart.framework.bean.Request;
import me.w1992wishes.smart.framework.util.ArrayUtil;
import me.w1992wishes.smart.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by w1992wishes
 * on 2017/12/22.
 */
public final class ControllerHelper {

    //存放请求与处理器的映射关系
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        //获取所有的controller
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass : controllerClassSet){
                //获取controller类定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    for (Method method : methods){
                        //判断是否带Action注解
                        if (method.isAnnotationPresent(Action.class)){
                            //获取注解上的信息
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证url映射规则
                            if (mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
