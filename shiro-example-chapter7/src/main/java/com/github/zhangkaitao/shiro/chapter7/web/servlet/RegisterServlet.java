package com.github.zhangkaitao.shiro.chapter7.web.servlet;

import entity.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="registerServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String pass=req.getParameter("pass");

        User user=new User();
        user.setUsername(username);
        user.setPassword(pass);

        UserService userService=new UserServiceImpl();
        try {
            userService.createUser(user);
            //sendRedirect不行，需要通过另一个
            resp.sendRedirect(req.getContextPath()+"/registerSuc");
        }catch(Exception ex){
            req.getRequestDispatcher("WEB-INF/jsp/register_fail.jsp").forward(req,resp);
        }

    }
}
