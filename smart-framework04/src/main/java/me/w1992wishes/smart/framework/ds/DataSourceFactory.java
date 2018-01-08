package me.w1992wishes.smart.framework.ds;

import javax.sql.DataSource;

/**
 * 数据源工厂
 *
 */
public interface DataSourceFactory {

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    DataSource getDataSource();
}