package chapter7.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginServlet{
    //@ResponseBody
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public String login(String username, String password, Map model){
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);

        try {
            subject.login(token);
            model.put("subject",subject);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }

        if(error != null) {//出错了，返回登录页面
            model.put("error", error);
            return "login";
        } else {//登录成功
            return "loginSuccess";
        }
    }
}
