package iitu.edu.kz.AkseleuMaksat.Service;

import iitu.edu.kz.AkseleuMaksat.DTO.CommentDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment, Long newsId); // Method to create a comment with associated news
    List<CommentDTO> getAllComments(); // Get all comments
    CommentDTO getCommentById(Long id); // Get a comment by ID
    CommentDTO updateComment(Long id, CommentDTO commentDTO); // Update a comment
    boolean deleteComment(Long id); // Delete a comment
}