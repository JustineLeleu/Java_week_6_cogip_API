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

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;
    private final CompanyService companyService;
    //private final CompanyRepository companyRepository;

    public ContactController(ContactService contactService, CompanyService companyService){
        this.contactService = contactService;
        this.companyService = companyService;
        //this.companyRepository = companyRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContactById(@PathVariable Short id){
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<Object> createContact(
            @Valid @RequestBody ContactDto contactDto,
            @RequestParam Short companyId){
        Contact contact = contactDto.toContact();
        Company company = companyService.getCompany(companyId).orElseThrow(() -> new NoSuchElementException("No company by ID: " + companyId));
        System.out.println(company);
        contact.setCompany(company);
        contactService.createContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
        return new ResponseEntity<>("Contact updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable short id){
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact deleted", HttpStatus.OK);
    }
}
