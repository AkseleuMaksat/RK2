package iitu.edu.kz.AkseleuMaksat.Service.impl;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.News;
import iitu.edu.kz.AkseleuMaksat.Entity.Author;
import iitu.edu.kz.AkseleuMaksat.Repository.NewsRepository;
import iitu.edu.kz.AkseleuMaksat.Repository.AuthorRepository;
import iitu.edu.kz.AkseleuMaksat.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, AuthorRepository authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());

        // Check if the author exists before associating
        Author author = authorRepository.findById(newsDTO.getAuthorId()).orElse(null);
        if (author != null) {
            news.setAuthor(author);
        } else {
            // Handle the case where the author is not found
            throw new IllegalArgumentException("Author not found with ID: " + newsDTO.getAuthorId());
        }

        return mapToDTO(newsRepository.save(news));
    }

    @Override
    public NewsDTO updateNews(Long id, NewsDTO newsDTO) {
        Optional<News> existingNews = newsRepository.findById(id);
        if (existingNews.isPresent()) {
            News news = existingNews.get();
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());

            // Check if the author exists before associating
            Author author = authorRepository.findById(newsDTO.getAuthorId()).orElse(null);
            if (author != null) {
                news.setAuthor(author);
            } else {
                // Handle the case where the author is not found
                throw new IllegalArgumentException("Author not found with ID: " + newsDTO.getAuthorId());
            }

            return mapToDTO(newsRepository.save(news));
        }
        // Log for debugging if News is not found
        logger.debug("News with ID " + id + " not found for update.");
        return null;  // Return null if news not found
    }


    @Override
    public NewsDTO getNewsById(Long id) {
        return newsRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public long deleteNewsByAuthorIdNull() {
        // Delete all news where authorId is null
        return newsRepository.deleteByAuthorIdIsNull();
    }
    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteNews(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private NewsDTO mapToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setContent(news.getContent());

        // Check if the author is not null before accessing its properties
        if (news.getAuthor() != null) {
            newsDTO.setAuthorId(news.getAuthor().getId());
        } else {
            // If author is null, log a warning and set the authorId to null or some default value
            newsDTO.setAuthorId(null); // Or use a default ID if appropriate
            logger.warn("News with ID: {} has no associated author.", news.getId());
        }

        return newsDTO;
    }

}

