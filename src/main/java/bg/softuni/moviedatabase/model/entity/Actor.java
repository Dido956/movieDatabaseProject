package bg.softuni.moviedatabase.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
public class Actor extends BaseEntity{
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @ManyToMany(mappedBy = "cast")
    private List<Movie> movies;
}
