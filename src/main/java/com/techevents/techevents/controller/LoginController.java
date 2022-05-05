/*package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Users;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private IUsersService usersService;

    @GetMapping("/auth/register")
    public String registroForm(Model model){
        model.addAttribute("user", new Users());

        return "register";
    }

    @PostMapping("/auth/register")
    public String register(@Valid @ModelAttribute Users users, BindingResult result){

        if(result.hasErrors()){
            return  "redirect:/auth/register";
        }
        else{
            model
        }

    }
}
*/
