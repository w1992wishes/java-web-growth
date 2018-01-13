package me.w1992wishes.smart.plugin.security.realm;

import me.w1992wishes.smart.framework.util.CollectionUtil;
import me.w1992wishes.smart.plugin.security.SecurityConstant;
import me.w1992wishes.smart.plugin.security.SmartSecurity;
import me.w1992wishes.smart.plugin.security.passowrd.Md5CredentialsMatcher;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 基于smart的自定义Realm
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public class SmartCustomRealm extends AuthorizingRealm {

    private final SmartSecurity smartSecurity;

    public SmartCustomRealm (SmartSecurity smartSecurity){
        this.smartSecurity = smartSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null){
            throw new AuthorizationException("parameter principals is null");
        }

        String username = (String) super.getAvailablePrincipal(principals);

        Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);

        Set<String> permissionNameSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(permissionNameSet)){
            for (String roleName : roleNameSet){
                Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null){
            throw new AuthenticationException("parameter token is null");
        }

        String username = ((UsernamePasswordToken) token).getUsername();

        String password = smartSecurity.getPassword(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
    }

}
