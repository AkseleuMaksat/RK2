package iitu.edu.kz.AkseleuMaksat.Controller;

import iitu.edu.kz.AkseleuMaksat.DTO.CommentDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.Comment;
import iitu.edu.kz.AkseleuMaksat.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import iitu.edu.kz.AkseleuMaksat.DTO.CommentRequest;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        // Manual validation
        if (commentRequest.getContent() == null || commentRequest.getContent().isBlank()) {
            return new ResponseEntity<>("Content cannot be blank", HttpStatus.BAD_REQUEST);
        }
        if (commentRequest.getNewsId() == null) {
            return new ResponseEntity<>("News ID cannot be null", HttpStatus.BAD_REQUEST);
        }

        try {
            Comment comment = new Comment();
            comment.setContent(commentRequest.getContent());
            Comment createdComment = commentService.createComment(comment, commentRequest.getNewsId());
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        CommentDTO commentDTO = commentService.getCommentById(id);
        if (commentDTO == null) {
            return new ResponseEntity<>("Comment not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        // Manual validation
        if (commentDTO.getContent() == null || commentDTO.getContent().isBlank()) {
            return new ResponseEntity<>("Content cannot be blank", HttpStatus.BAD_REQUEST);
        }

        try {
            CommentDTO updatedComment = commentService.updateComment(id, commentDTO);
            if (updatedComment == null) {
                return new ResponseEntity<>("Comment not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        boolean deleted = commentService.deleteComment(id);
        if (!deleted) {
            return new ResponseEntity<>("Comment not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Comment deleted successfully.", HttpStatus.NO_CONTENT);
    }
}
