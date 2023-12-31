package bg.softuni.moviedatabase.model.entity;

import bg.softuni.moviedatabase.model.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 5, max = 16)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @NotNull
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "age")
    @Positive
    private Integer age;
    @Column(name = "country")
    private String country;

    @Column(name = "profile_visits")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileHits;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Movie> favouriteMovies;

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserEntity(String username, String password, Role role) {
        this.username= username;
        this.password= password;
        this.role= role;
    }
}
