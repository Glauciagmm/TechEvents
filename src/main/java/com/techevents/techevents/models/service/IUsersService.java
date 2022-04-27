package com.techevents.techevents.models.service;

import com.techevents.techevents.models.entity.Users;

import java.util.List;

public interface IUsersService {
    public List<Users> listarTodos();

    public void guardar(Users users);

    public Users buscarPorId(Long id);

    public void eliminar(Long id);
}
