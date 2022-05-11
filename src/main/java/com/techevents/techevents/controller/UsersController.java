package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.EventsRepository;
import com.techevents.techevents.repository.UsersRepository;
import com.techevents.techevents.service.IEventsService;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/views/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping("/login")
    public String login(Model model){
        Users users = new Users();
        model.addAttribute("users", users);

        return "/views/users/login";

    }

    @GetMapping("/logout")
    public String logout(){
        return "/views/users/logout";
    }

    @GetMapping("/index")
    public String index(Authentication auth, HttpSession session){
        String username = auth.getName();
        if(session.getAttribute("users") == null){
            Users users = usersService.findByUsername(username);
            users.setPassword(null);
            session.setAttribute("users", users);
        }else{
            Users users = usersService.findByUsername(username);
            users.setPassword(null);
            session.setAttribute("users", users);
        }
        return "/views/users/index";
    }

    @GetMapping("/userEventAdd/{id}")
    public String userEventAdd(Authentication auth, @PathVariable("id") Long idEvent){
        Events event = eventsService.findById(idEvent);

        String username = auth.getName();
        Users user = usersService.findByUsername(username);

        if (user.getEvents().contains(event)){
            System.out.println("Duplicated Event!");
        }
        else{
            event.setSigned(event.getSigned()+1);
            eventsRepository.save(event);
            System.out.println("Signed Up for the event" + event.getSigned());
            user.getEvents().add(event);
            usersRepository.save(user);
        }

        return "redirect:/views/users/index";
    }

    @GetMapping("/userEventRemove/{id}")
    public String userEventRemove(Authentication auth, @PathVariable ("id") Long idEvent){
        Events event = eventsService.findById(idEvent);

        String username = auth.getName();
        Users user = usersService.findByUsername(username);

        if (user.getEvents().contains(event)){
            event.setSigned(event.getSigned()-1);
            eventsRepository.save(event);
            System.out.println("Event Removed");
            user.getEvents().remove(event);
            usersRepository.save(user);
        }
        else{
            System.out.println("Event not find");
        }

        return "redirect:/views/users/index";
    }


    @GetMapping("/create")
    public String createANewUser (Model model) {

        Users users = new Users();
        /*List<Users> listUsers= usersService.listUsers();*/

        model.addAttribute("title", "Form: New User");
        model.addAttribute("users", users);
        /* model.addAttribute("users", listUsers);*/

        return "/views/users/frmUsers";
    }
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute Users users, BindingResult result,
                          Model model, RedirectAttributes attribute){
        /*List<Users> listUsers = usersService.listUsers();*/

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New Event");
            model.addAttribute("users", users);
            /*model.addAttribute("users", listUsers);*/
            System.out.println("Error with form");

            return "/views/users/frmUsers";
        }

        usersService.save(users);
        System.out.println("User Successfully saved!");
        attribute.addFlashAttribute("success","User Successfully saved!");
        return "redirect:/views/users/index";
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
                return "redirect:/views/users/index";
            }
        }else {
            System.out.println("Error:Errors with the Id!");
            attribute.addFlashAttribute("error","Attention: Errors with the Id");
            return "redirect:/views/users/index";
        }

        /*List<Users> listUsers = usersService.listUsers();*/

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
                return "redirect:/views/users/index";
            }
        }else {
            System.out.println("Error: Error with the Id");
            attribute.addFlashAttribute("error","Attention: error with the Id!");
            return "redirect:/views/users/index";
        }

        usersService.delete(idUsers);
        System.out.println("Successfully deleted!");
        attribute.addFlashAttribute("warning","Successfully deleted!");

        return "redirect:/views/users/index";
    }
}
