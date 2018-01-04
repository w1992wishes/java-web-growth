package me.w1992wishes.smart.framework;

import me.w1992wishes.smart.framework.bean.Data;
import me.w1992wishes.smart.framework.bean.Handler;
import me.w1992wishes.smart.framework.bean.Param;
import me.w1992wishes.smart.framework.bean.View;
import me.w1992wishes.smart.framework.helper.*;
import me.w1992wishes.smart.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求转发Servlet
 *
 * Created by w1992wishes
 * on 2017/12/22.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化相关Helper
        HelperLoader.init();

        //获取ServletContext对象(用于注册Servlet)
        ServletContext servletContext = config.getServletContext();

        //注册处理JSP的servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //注册处理静态资源的servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

        //初始化UploaderHelper
        UploadHelper.init(servletContext);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        //过滤/favicon.ico请求
        if (requestPath.equals("/favicon.ico")){
            return;
        }

        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null){
            //获取Controller类及实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Param param;
            if (UploadHelper.isMultipart(request)){
                param = UploadHelper.createParam(request);
            } else {
                param = RequestHelper.createParam(request);
            }

            Object result;
            Method actionMethod = handler.getActionMethod();
            Class<?>[] methodParams = actionMethod.getParameterTypes();
            if (ArrayUtil.isEmpty(methodParams)){
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            }else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }
            
            if (result instanceof View){
                handlerViewResult((View) result, request, response);
            } else {
                handlerDataResult((Data) result, response);
            }
        }
    }

    private void handlerDataResult(Data data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if (model != null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

    private void handlerViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)){
            if (path.startsWith("/")){
                response.sendRedirect(request.getContextPath() + path);
            }else {
                Map<String, Object> model = view.getModel();
                Set<Map.Entry<String, Object>> entrySet = model.entrySet();
                for (Map.Entry<String, Object> entry : entrySet){
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }
}
