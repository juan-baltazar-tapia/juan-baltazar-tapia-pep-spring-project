package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


@RestController
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService;

    public SocialMediaController(MessageService messageService, AccountService accountService) {
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message messageToCreate) {
        if (messageToCreate.getMessageText() == null ||
            messageToCreate.getMessageText().trim().isEmpty() ||
            messageToCreate.getMessageText().length() > 255 ||
            accountService.userExists(messageToCreate.getPostedBy()).isEmpty()
        ) {
            return ResponseEntity.badRequest().build();
        }

        Message result = messageService.persistMessage(messageToCreate);
        if (result != null) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().build();
        }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable("id") int id) {
        if (messageService.existsById(id)) {
            messageService.deleteMessage(id);
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok().build();
        }
    }
     /**
     * Sending an http request to GET localhost:8080/accounts/9998/messages (messages does NOT exist for user) 
     * 
     * Expected Response:
     *  Status Code: 200
     *  Response Body: 
     */
    @GetMapping("/accounts/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesById(@PathVariable("id") int id) {
        List<Message> response;
        if (!accountService.userExists(id).isEmpty()) {
            response = messageService.getAllMessagesById(id);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    /**
     * Sending an http request to GET localhost:8080/messages/100 (message id 100 does not exist)
     * 
     * Expected Response:
     *  Status Code: 200
     *  Response Body: 
     */
    @GetMapping("/messages/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable("id") int id) {
        Optional<Message> response;
        if (messageService.existsById(id)) {
            response = messageService.getMessageById(id);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(null);
        }
    }
      /**
     * Sending an http request to PATCH localhost:8080/messages/9999 (message id exists in db) and successful message text
     * 
     * Expected Response:
     *  Status Code: 200
     *  Response Body: 1 (one row modified)
     * String json = "{\"messageText\": \"text changed\"}";
     *   /**
     * Responde 400 if doesn't user doesnt exist, message is too long, or empty string
     */
    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
        System.out.println("MESSAGE TEXT:" + message.getMessageText() + "-----");
        System.out.println(message.getMessageText() == "");

        if (message.getMessageText() == "" ||
           message.getMessageText().length() > 255 ||
           !messageService.existsById(id)
        ) {
            return ResponseEntity.badRequest().build();
        } else {
            messageService.updateMessage(id, message.getMessageText());
            return ResponseEntity.ok(1);
        }
    }
   
}
