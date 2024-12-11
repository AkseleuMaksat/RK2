package iitu.edu.kz.AkseleuMaksat.Service.impl;

import iitu.edu.kz.AkseleuMaksat.Entity.Author;
import iitu.edu.kz.AkseleuMaksat.Repository.AuthorRepository;
import iitu.edu.kz.AkseleuMaksat.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author updatedAuthor = existingAuthor.get();
            updatedAuthor.setName(author.getName());
            return authorRepository.save(updatedAuthor);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
