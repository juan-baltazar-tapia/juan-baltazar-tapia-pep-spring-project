package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

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

    public void deleteMessage(int id){
       messageRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return messageRepository.existsById(id);
    }

    public List<Message> getAllMessagesById(int id) {
        return messageRepository.findByPostedBy(id);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    public void updateMessage(int id, String message) {
        messageRepository.updateMessageText(id, message);
    }
}