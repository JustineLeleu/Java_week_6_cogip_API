package week6.java.cogip.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week6.java.cogip.dtos.ContactDto;
import week6.java.cogip.dtos.ContactOptionalDto;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.service.CompanyService;
import week6.java.cogip.service.ContactService;

import java.util.NoSuchElementException;

// Controller for the contact table
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;
    private final CompanyService companyService;

    public ContactController(ContactService contactService, CompanyService companyService){
        this.contactService = contactService;
        this.companyService = companyService;
    }

    // Get method to get all the contacts
    @GetMapping
    public ResponseEntity<Object> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    // Get method to get the contact by the id
    // Require a path variable id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getContactById(@PathVariable Short id){
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    // Post method to post a new contact
    // Require a body with lastname, firstname, phone and email
    // Require a param companyId
    @PostMapping
    public ResponseEntity<Object> createContact(
            @Valid @RequestBody ContactDto contactDto,
            @RequestParam Short companyId){
        Contact contact = contactDto.toContact();
        Company company = companyService.getCompany(companyId).orElseThrow(() -> new NoSuchElementException("No company by ID: " + companyId));
        contact.setCompany(company);
        contactService.createContact(contact);
        return new ResponseEntity<>("Contact has been created", HttpStatus.CREATED);
    }

    // Put method to update a contact by id
    // Require a path variable id
    // Can be given a body with lastname, firstname, phone and/or email
    // Can be given a param companyId
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContact(
            @PathVariable Short id,
            @RequestBody @Valid ContactOptionalDto contactOptionalDto,
            @RequestParam (required = false) Short companyId){
        Contact contact = contactService.getContactById(id);
        if (companyId != null){
            Company company = companyService.getCompany(companyId).orElseThrow(() -> new NoSuchElementException("No company by ID: " + companyId));
            contact.setCompany(company);
        }
        contactOptionalDto.toContact(contact);
        contactService.createContact(contact);
        return new ResponseEntity<>("Contact with the id : " + contact.getId() + " has been updated", HttpStatus.OK);
    }

    // Delete method to delete a contact by id
    // Require a path variable id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable short id){
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact with the id : " + id + " has been deleted", HttpStatus.OK);
    }
}
