package com.cavaler.contactlist.repository;

import com.cavaler.contactlist.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
}
