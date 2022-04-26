package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
