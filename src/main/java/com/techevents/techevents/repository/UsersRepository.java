package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);
}
