package bg.softuni.moviedatabase.model.dto;

import bg.softuni.moviedatabase.model.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class UserProfileDTO {
    private String country;
    private Integer age;
    private String email;

    private List<Movie> favMovies;
}
