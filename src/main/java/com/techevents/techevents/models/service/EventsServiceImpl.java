package com.techevents.techevents.models.service;

import com.techevents.techevents.models.entity.Events;
import com.techevents.techevents.models.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl implements IEventsService{

    @Autowired
    public EventsRepository eventsRepository;

    @Override
    public List<Events> listarTodos() {return (List<Events>) eventsRepository.findAll();}

    @Override
    public void guardar(Events events){
        eventsRepository.save(events);
    }

    @Override
    public Events buscarPorId(Long id) {return eventsRepository.findById(id).orElse(null);}

    @Override
    public void eliminar(Long id){
        eventsRepository.deleteById(id);
    }

}
