package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.repository.EventsRepository;
import com.techevents.techevents.service.IEventsService;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping({"/index", "/home", "/"})

    public String index(HttpServletRequest request, Model model, Authentication auth){
        model.addAttribute("title", "List of Events");


        int page = 0; //default page number is 0 (yes it is weird)
        int size = 6; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("events", eventsRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"date"))));


        boolean isLoggedIn = false;
        if (auth != null){
            isLoggedIn = true;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        List<Events> listOfFeatured = eventsService.listFeatured();
        model.addAttribute("featured", listOfFeatured);

        return "home";
    }
}
