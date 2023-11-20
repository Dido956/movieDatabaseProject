package bg.softuni.moviedatabase.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Movie extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    @ManyToMany(fetch = FetchType.EAGER)
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
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @Column(name = "duration", nullable = false)
    private Time duration;
}
