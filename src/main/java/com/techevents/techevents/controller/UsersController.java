package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;
import com.techevents.techevents.service.IEventsService;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/views/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IEventsService eventsService;

    @GetMapping("/")
    public String listUsers(Model model){
        List<Users> listadoUsers = usersService.findAll();

        model.addAttribute("titulo", "List of users");
        model.addAttribute("users", listadoUsers);
        return"/views/users/list";
    }
    @GetMapping("/create")
    public String create (Model model) {

        Users users = new Users();
        /*List<Users> listUsers= usersService.listaUsers();*/

        model.addAttribute("title", "Form: New User");
        model.addAttribute("users", users);
        /* model.addAttribute("users", listUsers);*/

        return "/views/users/frmUsers";
    }
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute Users users, BindingResult result,
                          Model model, RedirectAttributes attribute){
        /*List<Users> listUsers = usersService.listaUsers();*/

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New User");
            model.addAttribute("users", users);
            /*model.addAttribute("users", listUsers);*/
            System.out.println("Error with the form");

            return "/views/users/frmUsers";
        }

        usersService.save(users);
        System.out.println("User successfully saved!");
        attribute.addFlashAttribute("success","User successfully saved!");
        return "redirect:/views/users/";
    }

    @GetMapping("/edit/{id}")
    public String editUser (@PathVariable("id") Long idUsers, Model model,
                          RedirectAttributes attribute){

        Users users = null;

        if(idUsers > 0) {
            users = usersService.findById(idUsers);

            if(users == null){
                System.out.println("Error: The indicated Id doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The indicated Id doesn't exist!");
                return "redirect:/views/users/";
            }
        }else {
            System.out.println("Error:Errors with the Id!");
            attribute.addFlashAttribute("error","Attention: Errors with the Id");
            return "redirect:/views/users/";
        }

        /*List<Users> listUsers = usersService.listaUsers();*/

        model.addAttribute("title", "Form: Edit User");
        model.addAttribute("users", users);
        /* model.addAttribute("users", listUsers);*/


        return "/views/users/frmUsers";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long idUsers, RedirectAttributes attribute){
        Users users = null;

        if(idUsers > 0) {
            users = usersService.findById(idUsers);

            if(users == null){
                System.out.println("Error:The indicated Id doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The indicated Id doesn't exist!");
                return "redirect:/views/users/";
            }
        }else {
            System.out.println("Error: Error with the Id");
            attribute.addFlashAttribute("error","Attention: error with the Id!");
            return "redirect:/views/users/";
        }

        usersService.delete(idUsers);
        System.out.println("Successfully deleted!");
        attribute.addFlashAttribute("warning","Successfully deleted!");

        return "redirect:/views/users/";
    }
    @GetMapping("/save/{id}")
    public String saveEvent (@PathVariable("id") Long idEvents, RedirectAttributes attribute){
        Events events = null;

        if(idEvents > 0) {
            events = eventsService.findById(idEvents);

            if(events == null){
                System.out.println("Error: You are already signed up!");
                attribute.addFlashAttribute("error","Attention: You are already signed up, choose another event!");
                return "redirect:/index/";
            }
        }else {
            System.out.println("Error: You are already signed up!");
            attribute.addFlashAttribute("error","Attention: You are already signed up, choose another event!");
            return "redirect:/index/";
        }

        eventsService.save(events);
        System.out.println("Added to your events!");
        attribute.addFlashAttribute("success","Successfully added to your events!");
        return "/views/users/index";

    }
    @GetMapping("/delete/{id}")
    public String deleteEvent (@PathVariable("id") Long idEvents, RedirectAttributes attribute){
        Events events = null;

        if(idEvents > 0) {
            events = eventsService.findById(idEvents);

            if(events == null){
                System.out.println("Error:This Event has already been eliminated!");
                attribute.addFlashAttribute("error","Attention: This Event has already been eliminated!");
                return "redirect:/views/users/index\"";
            }
        }else {
            System.out.println("Error: Try it again!");
            attribute.addFlashAttribute("error","Attention: Something gone wrong!");
            return "redirect:/views/users/index\"";
        }

        eventsService.delete(idEvents);
        System.out.println("Successfully deleted!");
        attribute.addFlashAttribute("warning","Successfully deleted!");

        return "redirect:/views/users/index";
    }

}
