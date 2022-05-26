package com.techevents.techevents.service;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServicesImpl implements IEventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public EventsServicesImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<Events> findAll(){
        return (List<Events>) eventsRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public List<Events> listFeatured(){
        return (List<Events>) eventsRepository.findByFeaturedIsTrue();
    }

    @Override
    public void save (Events events){
        eventsRepository.save(events);
    }

    @Override
    public Events findById(Long id){

        return eventsRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){
        eventsRepository.deleteById(id);
    }

    @Override
    public List<Users> listUsers(){
        return null;
    }


}
