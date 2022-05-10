package com.techevents.techevents.service;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;

import java.util.List;

public interface IEventsService {

    public List<Events> findAll();
    public List<Events> listFeatured();
    public void save(Events events);
    public Events findById(Long id);
    public void delete(Long id);
    List<Users> listUsers();
}
