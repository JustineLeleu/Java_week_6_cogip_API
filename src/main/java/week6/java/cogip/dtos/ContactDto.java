package week6.java.cogip.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import week6.java.cogip.entities.Contact;

@Data
public class ContactDto {

    @NotBlank(message = "Invalid first name: Empty first name")
    @NotNull(message = "Invalid first name: First name is NULL")
    @Size(min = 3, max = 30, message = "Invalid first name: Must be of 3 - 30 characters")
    private String firstname;

    @NotBlank(message = "Invalid last name: Empty last name")
    @NotNull(message = "Invalid last name: Last name is NULL")
    @Size(min = 3, max = 30, message = "Invalid last name: Must be of 3 - 30 characters")
    private String lastname;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Invalid email: Empty email")
    @NotNull(message = "Invalid email: email is NULL")
    @Email(message = "Invalid email", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    public Contact toContact(){
        Contact contact = new Contact();
        contact.setFirstName(firstname);
        contact.setLastName(lastname);
        contact.setPhone(phone);
        contact.setEmail(email);
        return contact;
    }
}
