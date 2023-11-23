package bg.softuni.moviedatabase.model.dto;

import bg.softuni.moviedatabase.model.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class UserMoviesDTO {
    private List<Movie> favouriteMovies;
}
