package week6.java.cogip.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.entities.Invoice;
import week6.java.cogip.service.CompanyService;
import week6.java.cogip.service.ContactService;
import week6.java.cogip.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
  
  private final InvoiceService invoiceService;
  private final ContactService contactService;
  private final CompanyService companyService;
  
  public InvoiceController(InvoiceService invoiceService, ContactService contactService, CompanyService companyService) {
    this.invoiceService = invoiceService;
    this.contactService = contactService;
    this.companyService = companyService;
  }
  
  @GetMapping
  public ResponseEntity<Object> getAllInvoices() {
    return ResponseEntity.ok(invoiceService.getAllInvoices());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Object> getInvoiceById(@PathVariable Short id) {
    try {
      if (invoiceService.getInvoiceById(id).isPresent()) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    } catch (ResponseStatusException e) {
      return new ResponseEntity<>(id + " Invoice not found", HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping
  public ResponseEntity<Object> createInvoice(@RequestParam Short contactId,
                                              @RequestParam Short companyId) {
    try {
      Company company = companyService.getCompany(companyId).orElseThrow();
      Contact contact = contactService.getContactById(contactId);
      invoiceService.createInvoice(contact, company);
      System.out.println(company);
      System.out.println(contact);
      return new ResponseEntity<>("Invoice has been created", HttpStatus.CREATED);
    } catch (ResponseStatusException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateInvoice(@PathVariable Short id,
                                              @RequestParam(required = false) Short contactId,
                                              @RequestParam(required = false) Short companyId) {
    try {
      if (companyId != null) {
        Company company = companyService.getCompany(companyId).orElseThrow();
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isPresent()) {
          invoice.get().setCompany(company);
          invoiceService.updateInvoice(id, invoice.get());
          return new ResponseEntity<>("Invoice with the id : " + id + " has been updated", HttpStatus.OK);
        } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
      } else if (contactId != null) {
        Contact contact = contactService.getContactById(contactId);
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isPresent()) {
          invoice.get().setContact(contact);
          invoiceService.updateInvoice(id, invoice.get());
          return new ResponseEntity<>("Invoice with the id : " + id + " has been updated", HttpStatus.OK);
        } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    } catch (ResponseStatusException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteInvoice(@PathVariable Short id) {
    invoiceService.deleteInvoice(id);
    return new ResponseEntity<>("Invoice with the id : \" + id + \" has been deleted" , HttpStatus.OK);
  }
  
}
