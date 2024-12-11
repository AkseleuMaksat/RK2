package iitu.edu.kz.AkseleuMaksat.DTO;

import lombok.Data;

@Data
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private Long authorId; // ID автора
}