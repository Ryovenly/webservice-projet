package com.akane.j2eetd.repositories;

import com.akane.j2eetd.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Message, Long> {

}
