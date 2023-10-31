package bg.softuni.moviedatabase.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    @Length(min = 6, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String password;
    @Size(min = 3, max = 20, message = "Confirm password length must be between 3 and 20 characters!")
    @NotNull
    private String confirmPassword;
}
