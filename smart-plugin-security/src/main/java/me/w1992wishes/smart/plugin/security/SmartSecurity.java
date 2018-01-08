package me.w1992wishes.smart.plugin.security;


import me.w1992wishes.smart.framework.helper.DatabaseHelper;

import java.util.Set;

/**
 * Created by w1992wishes
 * on 2018/1/8.
 */
public class SmartSecurity implements ISmartSecurity {

    @Override
    public String getPassword(String username) {
        String sql = "select password from user where username = ?";
        return DatabaseHelper.queryColumn(sql, username);
    }

    @Override
    public Set<String> getRoleNameSet(String username) {
        String sql = "select r.role_name from user u, user_role ur, role r where u.id = ur.user_id and r.id = ur.role_id and u.username = ?";
        return DatabaseHelper.queryColumnSet(sql, username);
    }

    @Override
    public Set<String> getPermissionNameSet(String roleName) {
        String sql = "select p.permission_name from role r, role_permission rp, permission p where r.id = rp.role_id and p.id = rp.permission_id and r.role_name = ?";
        return DatabaseHelper.queryColumnSet(sql, roleName);
    }
}
