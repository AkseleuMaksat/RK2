package iitu.edu.kz.AkseleuMaksat.Controller;

import iitu.edu.kz.AkseleuMaksat.DTO.TagDTO;
import iitu.edu.kz.AkseleuMaksat.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO createdTag = tagService.createTag(tagDTO);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
        TagDTO tagDTO = tagService.getTagById(id);
        if (tagDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tagDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        TagDTO updatedTag = tagService.updateTag(id, tagDTO);
        if (updatedTag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        boolean deleted = tagService.deleteTag(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}