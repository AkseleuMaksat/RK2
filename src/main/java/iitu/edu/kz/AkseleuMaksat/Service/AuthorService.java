package iitu.edu.kz.AkseleuMaksat.Service;

import iitu.edu.kz.AkseleuMaksat.Entity.Author;
import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}