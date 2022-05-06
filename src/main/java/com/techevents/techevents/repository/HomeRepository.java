package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface HomeRepository extends JpaRepository<Events, Long>, Repository<Events, Long> {
public List<Events> findByFeaturedIsTrue();
        }
