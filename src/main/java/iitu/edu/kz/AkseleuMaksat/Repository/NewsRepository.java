package iitu.edu.kz.AkseleuMaksat.Repository;

import iitu.edu.kz.AkseleuMaksat.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    // Find news by title
    List<News> findByTitle(String title);

    // Find news by id
    Optional<News> findById(Long id);
    long deleteByAuthorIdIsNull();

}
