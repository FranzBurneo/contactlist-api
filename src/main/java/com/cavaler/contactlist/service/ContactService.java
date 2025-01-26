package com.cavaler.contactlist.service;

import com.cavaler.contactlist.dto.ContactDTO;
import com.cavaler.contactlist.entity.Contact;
import com.cavaler.contactlist.exception.ResourceNotFoundException;
import com.cavaler.contactlist.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(Integer id){
        return contactRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Contact create(ContactDTO contactDTO){
        Contact contact = mapper.map(contactDTO, Contact.class);

        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, ContactDTO contactDTO){
        Contact contactFromDb = findById(id);
        mapper.map(contactDTO, contactFromDb);

        return contactRepository.save(contactFromDb);
    }

    public void deleteById(Integer id){
        Contact contactFromDb = findById(id);
        if (contactFromDb == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

}
