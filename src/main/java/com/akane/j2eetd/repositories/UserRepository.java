package com.akane.j2eetd.repositories;

import com.akane.j2eetd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByFirstName(String firstName);
}