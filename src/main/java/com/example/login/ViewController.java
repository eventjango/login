package com.example.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ViewController {


    @GetMapping("/view")
    public String view(@ModelAttribute("user") UserInfo userInfo){

        return "view";
    }


    @GetMapping("/client")
    public String client(@ModelAttribute("client") ClientInfo clientInfo){

        return "login";
    }



}
