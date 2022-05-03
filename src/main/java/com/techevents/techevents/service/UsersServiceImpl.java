package com.techevents.techevents.service;

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
    public List<Users> listaUsers() { return (List<Users>)usersRepository.findAll();}
}
