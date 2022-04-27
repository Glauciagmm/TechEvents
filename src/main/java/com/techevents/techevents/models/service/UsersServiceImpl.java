package com.techevents.techevents.models.service;

import com.techevents.techevents.models.entity.Users;
import com.techevents.techevents.models.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements IUsersService{

    @Autowired
    public UsersRepository usersRepository;

    @Override
    public List<Users> listarTodos() {return (List<Users>) usersRepository.findAll();}

    @Override
    public void guardar(Users users) {
        usersRepository.save(users);
    }

    @Override
    public Users buscarPorId(Long id) {return usersRepository.findById(id).orElse(null);}

    @Override
    public void eliminar(Long id){
        usersRepository.deleteById(id);
    }
}
