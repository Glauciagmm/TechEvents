package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.service.IEventsService;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private IUsersService usersService;

    @GetMapping({"/index", "/home", "/"})
    public String index(Model model){
        List<Events> listOfEvents = eventsService.findAll();

        model.addAttribute("title", "List of Events");
        model.addAttribute("events", listOfEvents);

        List<Events> listOfFeatured = eventsService.listFeatured();
        model.addAttribute("features", listOfFeatured);

        return "home";
    }
}
