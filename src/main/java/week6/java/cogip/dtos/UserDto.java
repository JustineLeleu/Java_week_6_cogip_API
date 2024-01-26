package week6.java.cogip.dtos;

// All the imports for the UserDto
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import week6.java.cogip.entities.User;

// The UserDto class is used to create a new user
// It is used in the UserController

// @Data is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Data
public class UserDto {
  // The @NotBlank annotation is used to validate that the annotated string is not null or empty
  // The @NotNull annotation is used to validate that the annotated element is not null
  // The @Size annotation is used to validate that the annotated string size is between the specified boundaries
  @NotBlank(message = "Invalid username: Empty username")
  @NotNull(message = "Invalid username: Username is NULL")
  @Size(min = 3, max = 30, message = "Invalid username: Must be of 3 - 30 characters")
  private String username;
  
  // The @Pattern annotation is used to validate that the annotated string matches the regular expression
  @NotBlank(message = "Invalid password: Empty password")
  @NotNull(message = "Invalid password: Password is NULL")
  @Size(min = 6, max = 30, message = "Invalid password: Must be of 3 - 30 characters")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,30}$",
          message = "Invalid password, your password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
  private String password;
  
  // The dtoUser method is used to create a new user
  public User dtoUser(User user) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setUsername(this.username);
      user.setPassword(encoder.encode(this.password));
      return user;
  }
}
