package iitu.edu.kz.AkseleuMaksat.DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long newsId;
    private String created; // ISO-8601 formatted date-time string
}