package iitu.edu.kz.AkseleuMaksat.Controller;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsTagDTO;
import iitu.edu.kz.AkseleuMaksat.Service.NewsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newstags")
public class NewsTagController {

    private final NewsTagService newsTagService;

    @Autowired
    public NewsTagController(NewsTagService newsTagService) {
        this.newsTagService = newsTagService;
    }

    @PostMapping
    public ResponseEntity<NewsTagDTO> createNewsTag(@RequestBody NewsTagDTO newsTagDTO) {
        NewsTagDTO createdNewsTag = newsTagService.createNewsTag(newsTagDTO);
        return new ResponseEntity<>(createdNewsTag, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NewsTagDTO>> getAllNewsTags() {
        List<NewsTagDTO> newsTags = newsTagService.getAllNewsTags();
        return new ResponseEntity<>(newsTags, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsTag(@PathVariable Long id) {
        boolean deleted = newsTagService.deleteNewsTag(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}