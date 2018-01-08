package me.w1992wishes.smart.plugin.security.passowrd;

import me.w1992wishes.smart.framework.util.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * MD5密码匹配器
 *
 * Created by w1992wishes
 * on 2018/1/8.
 */
public class Md5CredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //获取表单提交的密码、明文，尚未加密
        String submitted = String.valueOf(((UsernamePasswordToken) token).getPassword());
        //获取数据库中已经加密的密码
        String encrypted = String.valueOf(info.getCredentials());
        return CodecUtil.md5(submitted).equals(encrypted);
    }

}
