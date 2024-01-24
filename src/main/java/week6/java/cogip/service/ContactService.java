package week6.java.cogip.service;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.repository.ContactRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService {

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts(){
        return (List<Contact>) contactRepository.findAll();
    }

    public Contact getContactById(Short id){
        return contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No contact by ID: " + id));
    }

    public void createContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(short id){
        if (contactRepository.existsById(id)) contactRepository.deleteById(id);
        else throw new NoSuchElementException("No contact by ID: " + id);
    }

}
