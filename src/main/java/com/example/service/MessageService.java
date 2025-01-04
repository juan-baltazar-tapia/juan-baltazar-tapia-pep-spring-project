package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /**
     * Given a brand new transient Message (meaning, no such Message exists yet in the database),
     * persist the Message to the database (create a new database record for the Message entity.)
     */
    public Message persistMessage(Message Message){
        return messageRepository.save(Message);
    }
}