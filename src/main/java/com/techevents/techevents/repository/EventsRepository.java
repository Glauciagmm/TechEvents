package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Events;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<Events, Long> {
}


