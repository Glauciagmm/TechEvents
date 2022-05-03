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
        public String listarEvents(Model model){
            List<Events> listadoEvents = eventsService.listarTodos();

            model.addAttribute("titulo", "Lista de Events");
            model.addAttribute("events", listadoEvents);
            return "/views/admin/listar";
        }

        @GetMapping("/create")
        public String crear (Model model){

            Events events = new Events();
            /*List<Users> listUsers= usersService.listaUsers();*/

            model.addAttribute("titulo", "Form: New Event");
            model.addAttribute("events", events);
           /* model.addAttribute("users", listUsers);*/

            return "/views/admin/frmCrear";
        }

        @PostMapping("/save")
        public String guardar(@Valid @ModelAttribute Events events, BindingResult result,
                              Model model, RedirectAttributes attribute){
            /*List<Users> listUsers = usersService.listaUsers();*/

            if (result.hasErrors()){
                model.addAttribute("titulo", "Form: New Event");
                model.addAttribute("events", events);
                /*model.addAttribute("users", listUsers);*/
                System.out.println("Hubo errores en el formulario");

                return "/views/admin/frmCrear";
            }

           eventsService.guardar(events);
            System.out.println("Evento guardado con exito!");
            attribute.addFlashAttribute("sucess","Evento guardado con exito");
            return "redirect:/views/admin/";
        }

        @GetMapping("/edit/{id}")
        public String editar (@PathVariable("id") Long idEvents, Model model,
                              RedirectAttributes attribute){

            Events events = null;

            if(idEvents > 0) {
                events = eventsService.buscadorPorId(idEvents);

                if(events == null){
                    System.out.println("Error: El Id indicado no existe!");
                    attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                    return "redirect:/views/admin/";
                }
            }else {
                System.out.println("Error: Hay un error con el Id!");
                attribute.addFlashAttribute("error","Atención: error con el Id!");
                return "redirect:/views/admin/";
            }

            /*List<Users> listUsers = usersService.listaUsers();*/

            model.addAttribute("titulo", "Form: Edit Event");
            model.addAttribute("events", events);
           /* model.addAttribute("users", listUsers);*/


            return "/views/admin/frmCrear";
        }

        @GetMapping("/delete/{id}")
        public String eliminar (@PathVariable("id") Long idEvents, RedirectAttributes attribute){
            Events events = null;

            if(idEvents > 0) {
                events = eventsService.buscadorPorId(idEvents);

                if(events == null){
                    System.out.println("Error: El Id indicado no existe!");
                    attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                    return "redirect:/views/admin/";
                }
            }else {
                System.out.println("Error: Hay un error con el Id!");
                attribute.addFlashAttribute("error","Atención: error con el Id!");
                return "redirect:/views/admin/";
            }

            eventsService.eliminar(idEvents);
            System.out.println("Registro eliminado con éxito!");
            attribute.addFlashAttribute("warning","Registro eliminado con éxito!");

            return "redirect:/views/admin/";
        }

}

