package com.techevents.techevents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/views/eventos")
public class AdminController {

        @Autowired
        private IClienteService clienteService;

        @Autowired
        private ICiudadService ciudadService;

        @GetMapping("/")
        public String listarClientes(Model model){
            List<Cliente> listadoClientes = clienteService.listarTodos();

            model.addAttribute("titulo", "Lista de Clientes");
            model.addAttribute("clientes", listadoClientes);
            return "/views/clientes/listar";
        }

        @GetMapping("/create")
        public String crear (Model model){

            Cliente cliente = new Cliente();
            List<Ciudad> listCiudades = ciudadService.listaCiudades();

            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("ciudades", listCiudades);

            return "/views/clientes/frmCrear";
        }

        @PostMapping("/save")
        public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
                              Model model, RedirectAttributes attribute){
            List<Ciudad> listCiudades = ciudadService.listaCiudades();

            if (result.hasErrors()){
                model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                model.addAttribute("cliente", cliente);
                model.addAttribute("ciudades", listCiudades);
                System.out.println("Hubo errores en el formulario");

                return "/views/clientes/frmCrear";
            }

            clienteService.guardar(cliente);
            System.out.println("Cliente guardado con exito!");
            attribute.addFlashAttribute("sucess","Cliente guardado con exito");
            return "redirect:/views/clientes/";
        }

        @GetMapping("/edit/{id}")
        public String editar (@PathVariable("id") Long idCliente, Model model,
                              RedirectAttributes attribute){

            Cliente cliente = null;

            if(idCliente > 0) {
                cliente = clienteService.buscadorPorId(idCliente);

                if(cliente == null){
                    System.out.println("Error: El Id indicado no existe!");
                    attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                    return "redirect:/views/clientes/";
                }
            }else {
                System.out.println("Error: Hay un error con el Id!");
                attribute.addFlashAttribute("error","Atención: error con el Id!");
                return "redirect:/views/clientes/";
            }

            List<Ciudad> listCiudades = ciudadService.listaCiudades();

            model.addAttribute("titulo", "Formulario: Editar Cliente");
            model.addAttribute("cliente", cliente);
            model.addAttribute("ciudades", listCiudades);


            return "/views/clientes/frmCrear";
        }

        @GetMapping("/delete/{id}")
        public String eliminar (@PathVariable("id") Long idCliente, RedirectAttributes attribute){
            Cliente cliente = null;

            if(idCliente > 0) {
                cliente = clienteService.buscadorPorId(idCliente);

                if(cliente == null){
                    System.out.println("Error: El Id indicado no existe!");
                    attribute.addFlashAttribute("error","Atención: El Id indicado no existe!");
                    return "redirect:/views/clientes/";
                }
            }else {
                System.out.println("Error: Hay un error con el Id!");
                attribute.addFlashAttribute("error","Atención: error con el Id!");
                return "redirect:/views/clientes/";
            }

            clienteService.eliminar(idCliente);
            System.out.println("Registro eliminado con éxito!");
            attribute.addFlashAttribute("warning","Registro eliminado con éxito!");

            return "redirect:/views/clientes/";
        }

}

