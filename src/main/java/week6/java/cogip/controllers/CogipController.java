package week6.java.cogip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import week6.java.cogip.entities.Company;
import week6.java.cogip.repository.CompanyRepository;
import week6.java.cogip.repository.ContactRepository;
import week6.java.cogip.repository.InvoiceRepository;
import week6.java.cogip.repository.UserRepository;

@RestController
public class CogipController {

    CompanyRepository companyRepository;
    ContactRepository contactRepository;
    InvoiceRepository invoiceRepository;
    UserRepository userRepository;

    public CogipController(CompanyRepository companyRepository, ContactRepository contactRepository, InvoiceRepository invoiceRepository, UserRepository userRepository){
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    public ResponseEntity<Object> addTest(){
        companyRepository.save(new Company((short) 1, "TestCo", "Belgium", "5%", "provider", "22-01-2024"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<Object> getTest(){
        companyRepository.save(new Company((short) 1, "TestCo", "Belgium", "5%", "provider", "22-01-2024"));
        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
    }
}
