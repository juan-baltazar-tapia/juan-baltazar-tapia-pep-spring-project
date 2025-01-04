package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService;

    public SocialMediaController(MessageService messageService) {
        this.messageService = messageService;
        //this.accountService = accountService;
    }

    // public SocialMediaController(AccountService accountService) {
    //     this.accountService = accountService;
    // }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message messageToCreate) {
        if (messageToCreate.getMessageText() == null ||
            messageToCreate.getMessageText().trim().isEmpty() ||
            messageToCreate.getMessageText().length() > 255
        ) {
            return ResponseEntity.badRequest().build();
        }

        Message result = messageService.persistMessage(messageToCreate);
        if (result != null) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().build();
        }
    
}
