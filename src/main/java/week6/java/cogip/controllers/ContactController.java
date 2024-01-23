package week6.java.cogip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.repository.CompanyRepository;
import week6.java.cogip.repository.ContactRepository;
import week6.java.cogip.service.CompanyService;
import week6.java.cogip.service.ContactService;

import java.awt.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;
    //private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public ContactController(ContactService contactService, CompanyRepository companyRepository){
        this.contactService = contactService;
        //this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Short id){
        System.out.println(id);
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createContact(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam Short companyId){
        Company company = companyRepository.findById(companyId).orElseThrow();
        Contact contact = new Contact(firstname, lastname, phone, email, company);
        contactService.createContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContact(
            @PathVariable Short id,
            @RequestParam (required = false) String firstname,
            @RequestParam (required = false) String lastname,
            @RequestParam (required = false) String phone,
            @RequestParam (required = false) String email){
        Contact contact = contactService.getContactById(id);
        if (firstname != null) contact.setFirstName(firstname);
        if (lastname != null) contact.setLastName(lastname);
        if (phone != null) contact.setPhone(phone);
        if (email != null) contact.setEmail(email);
        return new ResponseEntity<>("Contact updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable short id){
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact deleted", HttpStatus.OK);
    }
}
