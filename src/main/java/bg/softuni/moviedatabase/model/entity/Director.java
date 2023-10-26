package bg.softuni.moviedatabase.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "director")
@NoArgsConstructor
@Getter
@Setter
public class Director extends BaseEntity{
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

}
