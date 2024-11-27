package com.mygroup.FirstSpring;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }
}
