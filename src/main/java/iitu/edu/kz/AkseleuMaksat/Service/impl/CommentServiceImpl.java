package iitu.edu.kz.AkseleuMaksat.Service.impl;

import iitu.edu.kz.AkseleuMaksat.DTO.CommentDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.Comment;
import iitu.edu.kz.AkseleuMaksat.Entity.News;
import iitu.edu.kz.AkseleuMaksat.Repository.CommentRepository;
import iitu.edu.kz.AkseleuMaksat.Repository.NewsRepository;
import iitu.edu.kz.AkseleuMaksat.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, NewsRepository newsRepository) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public Comment createComment(Comment comment, Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with ID: " + newsId));
        comment.setNews(news);
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with ID: " + id));

        existingComment.setContent(commentDTO.getContent());
        existingComment.setCreated(LocalDateTime.now());
        return mapToDTO(commentRepository.save(existingComment));
    }

    @Override
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setNewsId(comment.getNews().getId());
        dto.setCreated(comment.getCreated().toString());
        return dto;
    }
}
