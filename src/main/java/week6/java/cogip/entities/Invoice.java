package week6.java.cogip.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
    
    @Column(name = "timestamp")
    private String timestamp;
    
    @ManyToOne(
    		cascade = CascadeType.ALL
    		)
    @JoinColumn(name= "invoice_company_id")
    private Company company;
    
    @ManyToOne(
    		cascade = CascadeType.ALL
    		)
    @JoinColumn(name= "invoice_contact_id")
    private Contact contact;
    
    public Invoice(short id, String timestamp, Company company, Contact contact) {
        this.id = id;
        this.timestamp = timestamp;
        this.company = company;
        this.contact = contact;
    }

	public Invoice() {

	}
	
	public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
