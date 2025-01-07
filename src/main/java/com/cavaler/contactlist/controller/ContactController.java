package com.cavaler.contactlist.controller;

import com.cavaler.contactlist.entity.Contact;
import com.cavaler.contactlist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/contacts")
@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @GetMapping
    Iterable<Contact> list(){
        return contactRepository.findAll();
    }
}
