package com.ltstudy.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class L123Controller {
    @GetMapping("/123")
    public  String L123(){
        return "123";
    }
}
