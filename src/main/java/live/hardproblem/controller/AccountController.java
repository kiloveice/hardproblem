package live.hardproblem.controller;

import live.hardproblem.beans.HttpResponseEntity;
import live.hardproblem.dao.entity.Account;
import live.hardproblem.service.AccountService;
import live.hardproblem.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/put")
    public HttpResponseEntity put(@RequestBody Account account, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            String ip = IpUtil.getIpAddr(request);
            account.setRole(1);
            int flag = accountService.insert(account);
            if (flag > 0) {
                response.setCode("200");
                response.setMessage("insert account successfully!");
                log.warn(ip + " insert account userName: " + account.getUsername() + " nickName:" + account.getNickname());
            } else {
                response.setCode("202");
                response.setMessage("Failed insert account.");
                log.warn(ip + " faile to insert account userName: " + account.getUsername() + " nickName:" + account.getNickname());
            }
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Error!");
            log.warn(e.toString());
        }
        return response;
    }

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Account account, HttpServletRequest request) {
        HttpResponseEntity response = new HttpResponseEntity();
        String ip = IpUtil.getIpAddr(request);
        try {
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(account.getUsername());
//            token.setPassword(account.getPassword().toCharArray());
            token.setPassword(accountService.hashPasswordByUsername(account.getUsername(), account.getPassword()).toCharArray());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            response.setCode("200");
            response.setMessage("login successfully!");
            log.info(ip + " login Username: " + account.getUsername());
        } catch (AuthenticationException e) {
            log.info(ip + " Failed to login." + e.toString());
            response.setCode("202");
            response.setMessage("Failed login.");
        } catch (Exception e) {
            log.info(e.toString());
            response.setCode("500");
            response.setMessage("Error");
        }
        return response;
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout() {
        HttpResponseEntity response = new HttpResponseEntity();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            response.setCode("200");
            response.setMessage("logout successfully!");
        } catch (Exception e) {
            log.warn(e.toString());
            response.setCode("500");
            response.setMessage("Failed logout");
        }
        return response;
    }
}
