package me.w1992wishes.smart.framework.ds;

import me.w1992wishes.smart.framework.helper.ConfigHelper;
import me.w1992wishes.smart.framework.util.ReflectionUtil;
import me.w1992wishes.smart.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InstanceFactory {

    /**
     * 用于缓存对应的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";

    /**
     * 获取 DataSourceFactory
     */
    public static DataSourceFactory getDataSourceFactory() {
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // 若缓存中存在对应的实例，则返回该实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // 从配置文件中获取相应的接口实现类配置
        String implClassName = ConfigHelper.getString(cacheKey);
        // 若实现类配置不存在，则使用默认实现类
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        // 通过反射创建该实现类对应的实例
        T instance = ReflectionUtil.newInstance(implClassName);
        // 若该实例不为空，则将其放入缓存
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        // 返回该实例
        return instance;
    }
}