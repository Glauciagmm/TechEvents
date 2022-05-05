package com.techevents.techevents.service;


import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;


import java.util.List;


public interface IUsersService {
    public List<Users> listarTodos();
    public void guardar(Users users);

    public Users buscadorPorId(Long id);
    public void eliminar(Long id);
    List<Events> listaEvents();
    public Users findByUsername(String username);
}
