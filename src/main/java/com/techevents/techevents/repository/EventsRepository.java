package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {
}
