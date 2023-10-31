package bg.softuni.moviedatabase.model.entity;

import bg.softuni.moviedatabase.model.entity.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Role role;
}
