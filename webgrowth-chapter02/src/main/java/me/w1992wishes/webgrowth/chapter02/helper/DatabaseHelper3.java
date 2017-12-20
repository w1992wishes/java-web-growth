package me.w1992wishes.webgrowth.chapter02.helper;

import me.w1992wishes.webgrowth.chapter02.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * DatabaseHelper3 已经封装的很好，每次调用的时候不需要主动去开启和关闭连接，
 * Connection的创建开启关闭对客户端都是透明的，但每次查询都需要去调用getConnection方法，
 * 还要CloseConnection方法，会频繁创建和销毁数据库连接，造成大量的系统开销，这时候可以用
 * 连接池进行优化，这里利用apache的dbcp连接池（pom中添加dbcp依赖）。
 *
 * Created by w1992wishes
 * on 2017/12/19.
 */
public final class DatabaseHelper3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper3.class);

    private static final QueryRunner QUERY_RUNNER;
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final BasicDataSource DATA_SOURCE;

    static {
        CONNECTION_HOLDER = new ThreadLocal<>();
        QUERY_RUNNER = new QueryRunner();

        Properties conf = PropsUtil.loadProps("config.properties");
        String  url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");
        String driver = conf.getProperty("jdbc.driver");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    /**
     * 查找实例类列表
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object ...params){
        List<T> entityList;
        try{
            Connection conn = getConnection();
            //QUERY_RUNNER 先返回一个ResultSet，然后通过反射去创建实体并初始化
            entityList = QUERY_RUNNER.query(conn, sql ,new BeanListHandler<T>(entityClass), params);
        }catch (SQLException e){
            LOGGER.error("query entity list failure" , e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 更新
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object ...params){
        int rows = 0;
        try{
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e){
            LOGGER.error("execute update failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return rows;
    }

    public static void executeSqlFile(String filePth){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePth);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while ((sql = reader.readLine()) != null){
                DatabaseHelper3.executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("execute sql file failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接，从连接池中获取
     *
     * @return
     */
    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null){
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
            return conn;
        }
        return conn;
    }

    public static void closeConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
