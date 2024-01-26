package week6.java.cogip.dtos;


import jakarta.validation.constraints.*;
import lombok.Data;
import week6.java.cogip.entities.Contact;

// DTO used as request body for the put method in the contact controller
// Used to validate the data and return corresponding error message
@Data
public class ContactOptionalDto {

    @Size(min = 3, max = 30, message = "Invalid first name: Must be of 3 - 30 characters")
    private String firstname;

    @Size(min = 3, max = 30, message = "Invalid last name: Must be of 3 - 30 characters")
    private String lastname;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phone;

    @Email(message = "Invalid email", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    public void toContact(Contact contact){
        if (firstname != null) contact.setFirstName(firstname);
        if (lastname != null) contact.setLastName(lastname);
        if (phone != null) contact.setPhone(phone);
        if (email != null) contact.setEmail(email);
    }
}
