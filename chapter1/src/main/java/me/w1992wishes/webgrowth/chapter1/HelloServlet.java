package me.w1992wishes.webgrowth.chapter1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w1992wishes on 2017/12/15.
 */
@WebServlet("/hello")//3.使用WebSocket注解，配置请求路径，对外发布servlet服务
public class HelloServlet extends HttpServlet{//1.继承HttpServlet，成为一个servlet

    @Override//2.覆盖doGet方法，接收get请求
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        req.setAttribute("currentTime", currentTime + "aaa");
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,
                resp);
    }
}
