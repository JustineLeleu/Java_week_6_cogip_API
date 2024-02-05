package week6.java.cogip.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Invalid username: Empty username")
    @NotNull(message = "Invalid username: Username is NULL")
    private String username;

    @NotBlank(message = "Invalid password: Empty password")
    @NotNull(message = "Invalid password: Password is NULL")
    private String password;
}
