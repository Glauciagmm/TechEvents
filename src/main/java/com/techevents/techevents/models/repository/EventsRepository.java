package com.techevents.techevents.models.repository;

import com.techevents.techevents.models.entity.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends CrudRepository<Events, Long> {
}
