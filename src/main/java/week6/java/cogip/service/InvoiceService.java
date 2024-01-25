package week6.java.cogip.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.entities.Invoice;
import week6.java.cogip.repository.CompanyRepository;
import week6.java.cogip.repository.ContactRepository;
import week6.java.cogip.repository.InvoiceRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InvoiceService {
  
  private final InvoiceRepository invoiceRepository;
  
  public InvoiceService(InvoiceRepository invoiceRepository, ContactRepository contactRepository, CompanyRepository companyRepository) {
    this.invoiceRepository = invoiceRepository;
  }
  
  public List<Invoice> getAllInvoices() {
    return (List<Invoice>) invoiceRepository.findAll();
  }
  public Optional<Invoice> getInvoiceById(Short id) {
    return invoiceRepository.findById(id);
  }
  
  public void createInvoice(Contact contact, Company company) {
    Invoice invoice = new Invoice();
    invoice.setContact(contact);
    invoice.setCompany(company);
    invoiceRepository.save(invoice);
  }
  
  public void updateInvoice(Short id, Invoice invoice) {
    invoice.setId(id);
    invoiceRepository.save(invoice);
  }
  
  public void deleteInvoice(Short id) {
    invoiceRepository.deleteById(id);
  }
  
  public void save(Optional<Invoice> invoice) {
    invoiceRepository.save(invoice.get());
  }
}
