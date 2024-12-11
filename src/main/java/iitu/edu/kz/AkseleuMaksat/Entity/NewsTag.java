package iitu.edu.kz.AkseleuMaksat.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "newstag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    // Getters and Setters for News and Tag (not the IDs directly)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}