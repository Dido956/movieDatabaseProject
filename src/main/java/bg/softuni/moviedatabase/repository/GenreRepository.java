package bg.softuni.moviedatabase.repository;

import bg.softuni.moviedatabase.model.entity.Genre;
import bg.softuni.moviedatabase.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

