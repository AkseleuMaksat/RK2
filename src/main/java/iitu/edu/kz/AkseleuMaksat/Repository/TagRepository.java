package iitu.edu.kz.AkseleuMaksat.Repository;

import iitu.edu.kz.AkseleuMaksat.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}