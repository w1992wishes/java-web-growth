package me.w1992wishes.smart.plugin.security.realm;

import me.w1992wishes.smart.framework.helper.DatabaseHelper;
import me.w1992wishes.smart.plugin.security.SecurityConfig;
import me.w1992wishes.smart.plugin.security.passowrd.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * 基于smart的JDBC Realm（需要提供相关smart.plugin.security.jdbc.* 配置项）
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm(){
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());//MD5加密算法
    }

}
