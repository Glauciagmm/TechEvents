package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);

    @Query(""+
    "SELECT CASE WHEN COUNT(S)>0 THEN "+
    "TRUE ELSE FALSE END "+
    "FROM Users s "+
    "WHERE s.email 0 ?1")

    Boolean selectExistsEmail(String email);

    void save();
}
