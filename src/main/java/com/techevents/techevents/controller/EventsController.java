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
@RequestMapping("/views/admin")
public class EventsController {

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private IUsersService usersService;

    @GetMapping("/")
    public String listEvents(Model model){
        List<Events> listOfEvents = eventsService.findAll();

        model.addAttribute("title", "List of Events");
        model.addAttribute("events", listOfEvents);
        return "/views/admin/list";
    }

    @GetMapping("/create")
    public String createNewEvent (Model model){

        Events events = new Events();
        /*List<Users> listUsers= usersService.listaUsers();*/

        model.addAttribute("title", "Form: New Event");
        model.addAttribute("events", events);
        /* model.addAttribute("users", listUsers);*/

        return "/views/admin/frmCreate";
    }

    @PostMapping("/save")
    public String saveEvent(@Valid @ModelAttribute Events events, BindingResult result,
                            Model model, RedirectAttributes attribute){
        /*List<Users> listUsers = usersService.listaUsers();*/

        if (result.hasErrors()){
            model.addAttribute("title", "Form: New Event");
            model.addAttribute("events", events);
            /*model.addAttribute("users", listUsers);*/
            System.out.println("Errors with the form");

            return "/views/admin/frmCreate";
        }

        eventsService.save(events);
        System.out.println("Successfully saved!");
        attribute.addFlashAttribute("success","Successfully saved");
        return "redirect:/views/admin/";
    }

    @GetMapping("/edit/{id}")
    public String editEvent (@PathVariable("id") Long idEvents, Model model,
                             RedirectAttributes attribute){

        Events events = null;

        if(idEvents > 0) {
            events = eventsService.findById(idEvents);

            if(events == null){
                System.out.println("Error: The ID doesn't exist!");
                attribute.addFlashAttribute("error","Attention: The ID doesn't exist!");
                return "redirect:/views/admin/";
            }
        }else {
            System.out.println("Error: Problem whit ID!");
            attribute.addFlashAttribute("error","Attention: Error with the indicate ID!");
            return "redirect:/views/admin/";
        }

        /*List<Users> listUsers = usersService.listUsers();*/

        model.addAttribute("title", "Form: Edit Event");
        model.addAttribute("events", events);
        /* model.addAttribute("users", listUsers);*/


        return "/views/admin/frmCreate";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent (@PathVariable("id") Long idEvents, RedirectAttributes attribute){
        Events events = null;

        if(idEvents > 0) {
            events = eventsService.findById(idEvents);

            if(events == null){
                System.out.println("Error: The ID doesn't not exist!");
                attribute.addFlashAttribute("error","Atention: The ID doesn't exist!");
                return "redirect:/views/admin/";
            }
        }else {
            System.out.println("Error: Error with the indicated ID!");
            attribute.addFlashAttribute("error","Atention: Error with the indicated ID!");
            return "redirect:/views/admin/";
        }

        eventsService.delete(idEvents);
        System.out.println("Successfully Removed!");
        attribute.addFlashAttribute("warning","Successfully Removed!");

        return "redirect:/views/admin/";
    }

}

