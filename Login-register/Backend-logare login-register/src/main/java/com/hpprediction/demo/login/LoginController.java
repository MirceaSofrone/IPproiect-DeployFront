package com.hpprediction.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/v1/login")
public class LoginController {

    @GetMapping
    public String login(){
        return "login";
    }

    @GetMapping(path = "noindex")
    public String noindex(){
        return "noindex";
    }


}
