package iitu.edu.kz.AkseleuMaksat.Controller;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsDTO;
import iitu.edu.kz.AkseleuMaksat.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // GET all news
    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> newsList = newsService.getAllNews();
        if (newsList.isEmpty()) {
            System.out.println("No news DTOs found in the service.");
        }
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    // GET news by ID
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        logger.debug("Received request to get news with ID: {}", id);

        NewsDTO news = newsService.getNewsById(id);
        if (news != null) {
            logger.debug("News with ID: {} found", id);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            logger.error("News with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteByAuthorIdNull")
    public ResponseEntity<String> deleteNewsByAuthorIdNull() {
        try {
            long deletedCount = newsService.deleteNewsByAuthorIdNull();
            if (deletedCount > 0) {
                return new ResponseEntity<>("Deleted " + deletedCount + " news items with null authorId", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No news found with null authorId", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting news.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST: Create a new news item
    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        try {
            NewsDTO createdNews = newsService.createNews(newsDTO);
            return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @RequestBody NewsDTO newsDTO) {
        try {
            // Log the incoming request
            logger.info("Attempting to update news with ID: {}", id);

            NewsDTO updatedNews = newsService.updateNews(id, newsDTO);
            if (updatedNews != null) {
                logger.info("News with ID: {} updated successfully.", id);
                return new ResponseEntity<>(updatedNews, HttpStatus.OK);
            } else {
                logger.error("News with ID: {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log exception
            logger.error("Error updating news with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE: Delete news by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        boolean isDeleted = newsService.deleteNews(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
