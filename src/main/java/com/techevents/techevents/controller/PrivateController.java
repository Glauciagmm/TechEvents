package com.techevents.techevents.controller;


import com.techevents.techevents.entity.Users;
import com.techevents.techevents.service.IUsersService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/private")
public class PrivateController {

    @Autowired
    private IUsersService usersService;

    @GetMapping("/index")
    public String index(Authentication auth, HttpSession session){
        String username = auth.name();
        if(session.getAttribute("user") == null){
            Users user = usersService.findByUsername(username);
            user.setPassword(null);
            session.setAttribute("user", user);
        }
        return "/index";
    }
}



