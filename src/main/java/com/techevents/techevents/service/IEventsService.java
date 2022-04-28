package com.techevents.techevents.service;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;

import java.util.List;

public interface IEventsService {

    public List<Events> listarTodos();
    public List<Events> listarDestacados();
    public void guardar(Events events);
    public Events buscadorPorId(Long id);
    public void eliminar(Long id);


    List<Users> listaUsers();
}
