package live.hardproblem.config.shiro;

import live.hardproblem.dao.entity.Account;
import live.hardproblem.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        Set<String> roles = new HashSet<>();
        if (account.getRole() == 2) {
            roles.add("admin");
        } else {
            roles.add("normal");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            String username = ((UsernamePasswordToken) token).getUsername();
            Account account = accountService.getByUsername(username);
            return new SimpleAuthenticationInfo(account, account.getPassword(), ByteSource.Util.bytes(account.getSalt()), account.getNickname());
        } catch (Exception ignored) {
        }
        return null;
    }
}
