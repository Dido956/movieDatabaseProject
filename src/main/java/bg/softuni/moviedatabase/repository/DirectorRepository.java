package bg.softuni.moviedatabase.repository;

import bg.softuni.moviedatabase.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Director findDirectorByName(String name);
}
