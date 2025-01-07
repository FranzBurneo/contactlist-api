package com.cavaler.contactlist.service;

import com.cavaler.contactlist.entity.Contact;
import com.cavaler.contactlist.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Integer id){
        return contactRepository
                .findById(id)
                .orElse(null);
    }

    public Contact create(Contact contact){
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, Contact contact){
        Contact contactFromDb = findById(id);
        if (contactFromDb == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }
        contact.setId(contactFromDb.getId());
        contact.setCreatedAt(contactFromDb.getCreatedAt());
        return contactRepository.save(contact);
    }

    public void deleteById(Integer id){
        Contact contactFromDb = findById(id);
        if (contactFromDb == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

}
