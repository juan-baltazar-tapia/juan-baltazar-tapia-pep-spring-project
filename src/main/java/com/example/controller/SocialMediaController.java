package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message messageToCreate) {
        if (messageToCreate.getMessageText() == null ||
            messageToCreate.getMessageText().trim().isEmpty() ||
            messageToCreate.getMessageText().length() > 255 //||
           // accountService.userExists(messageToCreate.getPostedBy())
        ) {
            return ResponseEntity.badRequest().build();
        }


        Message result = messageService.persistMessage(messageToCreate);
        //System.out.println("Result" + result);
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
    
}
