package iitu.edu.kz.AkseleuMaksat.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne // связь с автором
    @JoinColumn(name = "authorid", referencedColumnName = "id", nullable = false)
    private Author author;

}