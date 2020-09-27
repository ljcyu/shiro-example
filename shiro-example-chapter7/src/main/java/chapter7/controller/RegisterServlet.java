package chapter7.controller;

import chapter7.entity.User;
import chapter7.service.UserService;
import chapter7.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@Controller
public class RegisterServlet extends HttpServlet {

    @Autowired
    UserService userService;

    @RequestMapping("/showRegister")
    public String show(){
        return "register";
    }
    @RequestMapping("/regSuc")
    public String regSuc(){
        return "register_suc";
    }
    @RequestMapping("/regFail")
    public String regFail(){
        return "register_fail";
    }

    @RequestMapping("/register")
    public String register(String username,String pass) {
        User user=new User();
        user.setUsername(username);
        user.setPassword(pass);
        try {
            userService.createUser(user);
            //sendRedirect不行，需要通过另一个
            return "redirect:regSuc";
        }catch(Exception ex){
            return "redirect:regFail";
        }
    }
}
