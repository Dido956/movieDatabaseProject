package bg.softuni.moviedatabase.model.entity;

import bg.softuni.moviedatabase.model.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class UserEntity extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 5,max = 16)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @NotNull
    private String confirmPassword;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private List<UserRole> roles;
    @Enumerated(EnumType.STRING)
    private Role role;
}
