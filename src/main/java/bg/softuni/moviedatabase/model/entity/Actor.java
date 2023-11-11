package bg.softuni.moviedatabase.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actors")
@NoArgsConstructor
@Getter
@Setter
public class Actor extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @ManyToMany(mappedBy = "cast", fetch = FetchType.EAGER)
    private List<Movie> movies;
}
