package week6.java.cogip.controllers;

import jakarta.validation.Valid;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import week6.java.cogip.dtos.ContactDto;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.repository.CompanyRepository;
import week6.java.cogip.repository.ContactRepository;
import week6.java.cogip.service.CompanyService;
import week6.java.cogip.service.ContactService;

import java.awt.*;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Object> getContactById(@PathVariable Short id){
//        try{
//            Contact contact = contactService.getContactById(id);
//            return ResponseEntity.ok(contact);
//        }catch (NoSuchElementException e){
//            return new ResponseEntity<>("id " + id + " not found", HttpStatus.NOT_FOUND);
//        }

        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<Object> createContact(
            @RequestBody @Valid ContactDto contactDto,
            @RequestParam Short companyId){
        try{
            Company company = companyRepository.findById(companyId).orElseThrow();
            Contact contact = contactDto.toContact();
            contact.setCompany(company);
            contactService.createContact(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Contact not created", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContact(
            @PathVariable Short id,
            @RequestParam (required = false) String firstname,
            @RequestParam (required = false) String lastname,
            @RequestParam (required = false) String phone,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) Short companyId){
        try{
            Contact contact = contactService.getContactById(id);
            if (firstname != null) contact.setFirstName(firstname);
            if (lastname != null) contact.setLastName(lastname);
            if (phone != null) contact.setPhone(phone);
            if (email != null) contact.setEmail(email);
            if (companyId != null){
                // get company id
            }
            contactService.createContact(contact);
            return new ResponseEntity<>("Contact updated", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable short id){
        try{
            contactService.deleteContact(id);
            return new ResponseEntity<>("Contact deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
