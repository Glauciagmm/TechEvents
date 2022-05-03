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

    @Override
    public List<Events> listarTodos(){
        return (List<Events>) eventsRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public List<Events> listarDestacados(){
        return (List<Events>) eventsRepository.findByFeaturedIsTrue();
    }

    @Override
    public void guardar (Events events){
        eventsRepository.save(events);
    }

    @Override
    public Events buscadorPorId(Long id){

        return eventsRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id){
        eventsRepository.deleteById(id);
    }

    @Override
    public List<Users> listaUsers(){
        return null;
    }


}
