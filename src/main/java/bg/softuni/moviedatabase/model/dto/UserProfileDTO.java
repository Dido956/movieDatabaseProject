package bg.softuni.moviedatabase.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserProfileDTO {
    private String country;
    private Integer age;
    private String email;
}
