package com.techevents.techevents.controller;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;
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

    @GetMapping("/")
    public String listarUsers(Model model){
        List<Users> listadoUsers = usersService.listarTodos();

        model.addAttribute("titulo", "Lista de usuarios");
        model.addAttribute("users", listadoUsers);
        return"/views/users/listar";
    }
    @GetMapping("/create")
    public String crear (Model model) {

        Users users = new Users();
        /*List<Users> listUsers= usersService.listaUsers();*/

        model.addAttribute("titulo", "Form: New User");
        model.addAttribute("users", users);
        /* model.addAttribute("users", listUsers);*/

        return "/views/users/frmUsers";
    }
    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute Users users, BindingResult result,
                          Model model, RedirectAttributes attribute){
        /*List<Users> listUsers = usersService.listaUsers();*/

        if (result.hasErrors()){
            model.addAttribute("titulo", "Form: New Event");
            model.addAttribute("users", users);
            /*model.addAttribute("users", listUsers);*/
            System.out.println("Hubo errores en el formulario");

            return "/views/users/frmUsers";
        }

        usersService.guardar(users);
        System.out.println("Usuario guardado con exito!");
        attribute.addFlashAttribute("sucess","Evento guardado con exito");
        return "redirect:/views/users/";
    }

    @GetMapping("/edit/{id}")
    public String editar (@PathVariable("id") Long idUsers, Model model,
                          RedirectAttributes attribute){

        Users users = null;

        if(idUsers > 0) {
            users = usersService.buscadorPorId(idUsers);

            if(users == null){
                System.out.println("Error: El Id indicado no existe!");
                attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                return "redirect:/views/users/";
            }
        }else {
            System.out.println("Error: Hay un error con el Id!");
            attribute.addFlashAttribute("error","Atención: error con el Id!");
            return "redirect:/views/users/";
        }

        /*List<Users> listUsers = usersService.listaUsers();*/

        model.addAttribute("titulo", "Form: Edit User");
        model.addAttribute("users", users);
        /* model.addAttribute("users", listUsers);*/


        return "/views/users/frmUsers";
    }

    @GetMapping("/delete/{id}")
    public String eliminar (@PathVariable("id") Long idUsers, RedirectAttributes attribute){
        Users users = null;

        if(idUsers > 0) {
            users = usersService.buscadorPorId(idUsers);

            if(users == null){
                System.out.println("Error: El Id indicado no existe!");
                attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                return "redirect:/views/users/";
            }
        }else {
            System.out.println("Error: Hay un error con el Id!");
            attribute.addFlashAttribute("error","Atención: error con el Id!");
            return "redirect:/views/users/";
        }

        usersService.eliminar(idUsers);
        System.out.println("Registro eliminado con éxito!");
        attribute.addFlashAttribute("warning","Registro eliminado con éxito!");

        return "redirect:/views/users/";
    }
}
