package com.techevents.techevents.models.service;

import com.techevents.techevents.models.entity.Events;

import java.util.List;

public interface IEventsService {
    public List<Events> listarTodos();

    public void guardar(Events events);

    public Events buscarPorId(Long id);

    public void eliminar(Long id);
}
