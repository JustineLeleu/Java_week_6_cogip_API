package week6.java.cogip.service;

import org.springframework.stereotype.Service;
import week6.java.cogip.entities.Company;
import week6.java.cogip.entities.Contact;
import week6.java.cogip.entities.Invoice;
import week6.java.cogip.repository.CompanyRepository;
import week6.java.cogip.repository.ContactRepository;
import week6.java.cogip.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
  
  // final variable + controller below replace @Autowired from Spring ( not enough secure )
  // InvoiceRepository is used to call the methods from the repository
  // InvoiceRepository is injected in the controller with the constructor
  
  private final InvoiceRepository invoiceRepository;
  public InvoiceService(InvoiceRepository invoiceRepository, ContactRepository contactRepository, CompanyRepository companyRepository) {
    this.invoiceRepository = invoiceRepository;
  }
  
  // Get "all" invoices from the database
  public List<Invoice> getAllInvoices() {
    return (List<Invoice>) invoiceRepository.findAll();
  }
  // Get a specific invoice by ID from the database
  public Optional<Invoice> getInvoiceById(Short id) {
    return invoiceRepository.findById(id);
  }
  
  // Create a new invoice in the database
  public void createInvoice(Contact contact, Company company) {
    Invoice invoice = new Invoice();
    invoice.setContact(contact);
    invoice.setCompany(company);
    invoiceRepository.save(invoice);
  }
  // Update an invoice in the database
  public void updateInvoice(Short id, Invoice invoice) {
    invoice.setId(id);
    invoiceRepository.save(invoice);
  }
  // Delete an invoice in the database
  public void deleteInvoice(Short id) {
    Invoice invoice = invoiceRepository.findById(id).orElseThrow();
    invoice.getContact().getInvoices().remove(invoice);
    invoice.getCompany().getInvoices().remove(invoice);
    invoiceRepository.deleteById(id);
  }
  
  // Save an invoice in the database
  public void save(Optional<Invoice> invoice) {
    invoiceRepository.save(invoice.get());
  }
}
