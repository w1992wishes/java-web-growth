package me.w1992wishes.smart.plugin.security;

import me.w1992wishes.smart.framework.helper.ConfigHelper;
import me.w1992wishes.smart.framework.util.ReflectionUtil;

/**
 * 从配置文件读取相关属性
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public final class SecurityConfig {

    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static SmartSecurity getSmartSecurity(){
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        return (SmartSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCacheable(){
        return ConfigHelper.getBoolean(SecurityConstant.CACHEABLE);
    }

}
