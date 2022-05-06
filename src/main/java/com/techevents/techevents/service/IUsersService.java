package com.techevents.techevents.service;


import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;


import java.util.List;


public interface IUsersService {
    public List<Users> findAll();
    public void save(Users users);

    public Users findById(Long id);
    public void delete(Long id);
    List<Events> listEvents();

    Users findByUsername(String username);
}
