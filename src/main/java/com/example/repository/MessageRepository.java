package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(int id);
    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.id = :id")
    @Transactional
    void updateMessageText(@Param("id") int id, @Param("messageText") String message);
}

