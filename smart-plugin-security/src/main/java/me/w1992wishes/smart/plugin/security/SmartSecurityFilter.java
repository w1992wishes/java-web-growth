package me.w1992wishes.smart.plugin.security;

import me.w1992wishes.smart.plugin.security.realm.SmartCustomRealm;
import me.w1992wishes.smart.plugin.security.realm.SmartJdbcRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 安全过滤器
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public class SmartSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        //设置realm,可同时支持多个realm，并按照先后顺序用逗号隔开
        setRealms(webSecurityManager);
        //设置cache，减低数据库查询，降低io
        setCache(webSecurityManager);
    }

    private void setCache(WebSecurityManager webSecurityManager) {
        if (SecurityConfig.isCacheable()){
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
            //使用基于内存的CacheManager
            CacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }

    private void setRealms(WebSecurityManager webSecurityManager) {
        //读取smart.plugin.security.realms配置项
        String securityRealms = SecurityConfig.getRealms();
        if (securityRealms != null){
            //根据逗号拆分
            String[] securityRealmArray = securityRealms.split(",");
            if (securityRealmArray.length > 0){
                //使Realm具备唯一性与顺序性
                Set<Realm> realms = new LinkedHashSet<>();
                for (String securityRealm : securityRealmArray){
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
                        //添加基于JDBC的Realm，需配置相关SQL查询语句
                        addJdbcRealm(realms);
                    } else if (securityRealms.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
                        //添加基于定制化的Realm，需实现SmartSecurity接口
                        addCustomRealm(realms);
                    }
                    RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                    realmSecurityManager.setRealms(realms);//设置realm
                }
            }
        }
    }

    private void addJdbcRealm(Set<Realm> realms){
        //添加自己实现的基础JDBC的Realm
        SmartJdbcRealm smartJdbcRealm = new SmartJdbcRealm();
        realms.add(smartJdbcRealm);
    }

    private void addCustomRealm(Set<Realm>  realms){
        //读取smart.plugin.security.custom.class配置项
        SmartSecurity smartSecurity = SecurityConfig.getSmartSecurity();
        //添加自己实现的Realm
        SmartCustomRealm smartCustomRealm = new SmartCustomRealm(smartSecurity);
        realms.add(smartCustomRealm);
    }
}
