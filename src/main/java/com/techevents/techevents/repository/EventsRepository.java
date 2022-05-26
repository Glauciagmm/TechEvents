package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface EventsRepository extends JpaRepository<Events, Long>, Repository<Events, Long> {
    public List<Events> findByFeaturedIsTrue();
}
