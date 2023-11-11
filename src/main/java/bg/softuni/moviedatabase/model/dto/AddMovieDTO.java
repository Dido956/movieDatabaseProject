package bg.softuni.moviedatabase.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AddMovieDTO {
    private String title;
    private String summary;
    private Double rating;
    private String imageUrl;
    private String duration;
    private LocalDate releaseDate;
    private String directorName;

    private List<String> actorNames;

}
