package com.techevents.techevents.service;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements IUsersService{

@Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> findAll() {
        return (List<Users>)usersRepository.findAll();}

    @Override
    public void save(Users users){
        usersRepository.save(users);
    }

    @Override
    public Users findById(Long id){

        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){
        usersRepository.deleteById(id);
    }

    @Override
    public List<Events> listEvents(){
        return null;
    }

}
