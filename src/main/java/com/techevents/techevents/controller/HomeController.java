package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.service.IEventsService;
import com.techevents.techevents.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
@Controller
public class HomeController {
    @GetMapping({"/index", "/home", "/"})
    public String index(){
        return "home";
    }
}
*/
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private IUsersService usersService;

    @GetMapping({"/index", "/home", "/"})
    public String index(Model model, Authentication auth){
        List<Events> listadoEvents = eventsService.listarTodos();

        model.addAttribute("titulo", "Listado de Eventos");
        model.addAttribute("events", listadoEvents);

        boolean isLoggedIn = false;
        if (auth != null){
            isLoggedIn = true;
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        List<Events> listadoDestacados = eventsService.listarDestacados();
        model.addAttribute("destacados", listadoDestacados);

        return "home";
    }
}
