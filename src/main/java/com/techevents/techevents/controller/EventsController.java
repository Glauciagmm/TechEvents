package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
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
        public String create (Model model){

            Events events = new Events();
            /*List<Users> listUsers= usersService.listUsers();*/

            model.addAttribute("title", "Form: New Event");
            model.addAttribute("events", events);
           /* model.addAttribute("users", listUsers);*/

            return "/views/admin/frmCreate";
        }

        @PostMapping("/save")
        public String save(@Valid @ModelAttribute Events events, BindingResult result,
                              Model model, RedirectAttributes attribute){
            /*List<Users> listUsers = usersService.listUsers();*/

            if (result.hasErrors()){
                model.addAttribute("title", "Form: New Event");
                model.addAttribute("events", events);
                /*model.addAttribute("users", listUsers);*/
                System.out.println("Error in the form");

                return "/views/admin/frmCreate";
            }

           eventsService.save(events);
            System.out.println("Successfully Saved!");
            attribute.addFlashAttribute("success","Successfully Saved");
            return "redirect:/views/admin/";
        }

        @GetMapping("/edit/{id}")
        public String edit (@PathVariable("id") Long idEvents, Model model,
                              RedirectAttributes attribute){

            Events events = null;

            if(idEvents > 0) {
                events = eventsService.findById(idEvents);

                if(events == null){
                    System.out.println("Error: The Id doesn't exist!");
                    attribute.addFlashAttribute("error","Attention: The Id doesn't exist!");
                    return "redirect:/views/admin/";
                }
            }else {
                System.out.println("Error: error with the indicated Id!");
                attribute.addFlashAttribute("error","Attention: error with the indicated Id!");
                return "redirect:/views/admin/";
            }

            /*List<Users> listUsers = usersService.listUsers();*/

            model.addAttribute("title", "Form: Edit Event");
            model.addAttribute("events", events);
           /* model.addAttribute("users", listUsers);*/


            return "/views/admin/frmCreate";
        }

        @GetMapping("/delete/{id}")
        public String delete (@PathVariable("id") Long idEvents, RedirectAttributes attribute){
            Events events = null;

            if(idEvents > 0) {
                events = eventsService.findById(idEvents);

                if(events == null){
                    System.out.println("Error: The Id doesn't exist!");
                    attribute.addFlashAttribute("error","Attention: The Id doesn't exist!");
                    return "redirect:/views/admin/";
                }
            }else {
                System.out.println("Error: error with the indicated Id!");
                attribute.addFlashAttribute("error","Attention: error with the indicated Id!");
                return "redirect:/views/admin/";
            }

            eventsService.delete(idEvents);
            System.out.println("Successfully Deleted!");
            attribute.addFlashAttribute("warning","Successfully Deleted!");

            return "redirect:/views/admin/";
        }

}
