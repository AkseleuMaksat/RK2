package iitu.edu.kz.AkseleuMaksat.Repository;

import iitu.edu.kz.AkseleuMaksat.Entity.NewsTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTagRepository extends JpaRepository<NewsTag, Long> {
}