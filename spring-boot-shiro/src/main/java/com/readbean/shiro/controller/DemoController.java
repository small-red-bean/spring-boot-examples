package com.readbean.shiro.controller;

import com.readbean.shiro.conf.IShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("demo")
@Controller
public class DemoController {

    private IShiroRealm iShiroRealm;

    @RequestMapping("/shiro")
    public void shiro() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("1", "2");
        subject.login(token);
    }
}
