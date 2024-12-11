package iitu.edu.kz.AkseleuMaksat.Repository;

import iitu.edu.kz.AkseleuMaksat.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
