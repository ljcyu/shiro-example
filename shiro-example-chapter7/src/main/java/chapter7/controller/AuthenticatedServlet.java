package chapter7.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthenticatedServlet{
    @RequestMapping("/authenticated")
    public String get(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            return "authenticated";
        } else {
            return "login";
        }
    }


}
