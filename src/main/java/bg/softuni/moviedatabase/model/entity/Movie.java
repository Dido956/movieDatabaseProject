package bg.softuni.moviedatabase.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Getter
@Setter
public class Movie extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> cast;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @Column(name = "summary", length = 1000)
    private String summary;

}
