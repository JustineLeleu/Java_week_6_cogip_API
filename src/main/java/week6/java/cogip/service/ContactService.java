package week6.java.cogip.service;

import org.springframework.stereotype.Service;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.repository.ContactRepository;

import java.util.List;

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
        System.out.println(contactRepository.existsById(id));
        return contactRepository.findById(id).orElseThrow();
    }

    public void createContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(short id){
        contactRepository.deleteById(id);
    }

}
