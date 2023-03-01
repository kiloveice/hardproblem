package live.hardproblem.config.shiro;

import live.hardproblem.config.shiro.filter.JSONFormAuthenticationFilter;
import live.hardproblem.config.shiro.filter.JSONFormRolesAuthorizationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        MyRealm realm = new MyRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-256");
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(false);
        realm.setCredentialsMatcher(matcher);
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public SecureRandomNumberGenerator SecureRandomNumberGenerator() {
        return new SecureRandomNumberGenerator();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new JSONFormAuthenticationFilter());
        filterMap.put("roles", new JSONFormRolesAuthorizationFilter());
        factoryBean.setFilters(filterMap);

        Map<String, String> map = new LinkedHashMap<>();
//        map.put("/food/**", "authc");
        map.put("/admin/**", "roles[admin]");
        map.put("/**", "anon");
        factoryBean.setFilterChainDefinitionMap(map);

        return factoryBean;
    }


}
