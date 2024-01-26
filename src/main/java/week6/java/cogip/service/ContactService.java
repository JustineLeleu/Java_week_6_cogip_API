package week6.java.cogip.service;

import org.springframework.stereotype.Service;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.exceptions.CreationFailedException;
import week6.java.cogip.exceptions.DeleteFailedException;
import week6.java.cogip.repository.ContactRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService {

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    // Method to get and return all the contacts
    public List<Contact> getAllContacts(){
        return (List<Contact>) contactRepository.findAll();
    }

    // Method to get and return a contact by id
    // Return error if id not found
    public Contact getContactById(Short id){
        return contactRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No contact by ID: " + id));
    }

    // Method to create a contact
    public void createContact(Contact contact){
        contactRepository.save(contact);
        if (!contactRepository.existsById(contact.getId())) throw new CreationFailedException("Failed to create contact");
    }

    // Method to delete a contact and make sure it is deleted in all tables
    // Return error if id not found or delete failed
    public void deleteContact(short id){
        if (contactRepository.existsById(id)){
            Contact contact = contactRepository.findById(id).orElseThrow();
            contact.getCompany().getContacts().remove(contact);
            contactRepository.deleteById(id);
        }
        else throw new NoSuchElementException("No contact by ID: " + id);

        if (contactRepository.existsById(id)) throw new DeleteFailedException("Failed to delete contact");
    }

}
