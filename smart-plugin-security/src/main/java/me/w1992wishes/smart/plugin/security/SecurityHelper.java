package me.w1992wishes.smart.plugin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security助手类
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public final class SecurityHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

    public static void login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e){
                LOGGER.error("login failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    public static void logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null){
            currentUser.logout();
        }
    }

}
