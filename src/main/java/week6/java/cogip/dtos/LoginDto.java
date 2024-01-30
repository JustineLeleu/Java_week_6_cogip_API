package week6.java.cogip.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Invalid username: Empty username")
    @NotNull(message = "Invalid username: Username is NULL")
    @Size(min = 3, max = 30, message = "Invalid username: Must be of 3 - 30 characters")
    private String username;

    @NotBlank(message = "Invalid password: Empty password")
    @NotNull(message = "Invalid password: Password is NULL")
    @Size(min = 6, max = 30, message = "Invalid password: Must be of 3 - 30 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,30}$",
            message = "Invalid password, your password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
}
