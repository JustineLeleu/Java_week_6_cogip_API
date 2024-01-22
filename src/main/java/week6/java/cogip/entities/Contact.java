package week6.java.cogip.entities;

import jakarta.persistence.Entity;

@Entity
public class Contact {
    private short id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String timestamp;
    private short contactCompanyId;

    public Contact(short id, String firstName, String lastName, String phone, String email, String timestamp, short contactCompanyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.timestamp = timestamp;
        this.contactCompanyId = contactCompanyId;
    }

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getContactCompanyId() {
        return contactCompanyId;
    }

    public void setContactCompanyId(short contactCompanyId) {
        this.contactCompanyId = contactCompanyId;
    }
}
