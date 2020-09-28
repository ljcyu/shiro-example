package chapter7.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class AuthorizationExHandler {

    @ExceptionHandler
    @ResponseBody
    public String process(AuthorizationException ex){
        log.error("{}",ex.getMessage());
        return "权限验证失败 "+ex.getMessage();
    }
}
