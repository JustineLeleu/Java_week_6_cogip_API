package week6.java.cogip.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

// Contact entity connected to the database contact table
@Setter
@Getter
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false)
    private String timestamp;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties(value = { "contacts","invoices" })
    @JoinColumn(name = "contact_company_id")
    private Company company;

    @OneToMany(
            mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnoreProperties(value = { "company","contact" })
    private List<Invoice> invoices = new ArrayList<>();

    public Contact() {

    }

    public Contact(String firstName, String lastName, String phone, String email, Company company) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.company = company;
    }
    
}
