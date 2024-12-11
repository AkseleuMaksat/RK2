package iitu.edu.kz.AkseleuMaksat.Repository;

import iitu.edu.kz.AkseleuMaksat.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}