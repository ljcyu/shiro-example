package chapter7.controller;

import org.apache.shiro.authz.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotation")
public class AnnotatedConfigAction {
    //只有guest可以访问
    @RequiresGuest
    @RequestMapping("requires_guest")
    public String requiredGuest(){
        return "@RequiredGuest";
    }

    @RequiresAuthentication
    @RequestMapping("requires_authentication")
    public String requiresAuthentication(){
        return "RequiresAuthentication";
    }
    @RequiresUser
    @RequestMapping("requires_user")
    public String requiresUser(){
        return "@RequiresUser";
    }
    @RequiresRoles("test")
    @RequestMapping("requires_roles")
    public String requiresRoles(){
        return "@RequiresRoles";
    }
    @RequiresPermissions("test:list")
    @RequestMapping("requires_perms")
    public String requiresPerms(){
        return "@RequiresPermissions";
    }
}
