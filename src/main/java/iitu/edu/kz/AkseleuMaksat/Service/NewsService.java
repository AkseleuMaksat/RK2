package iitu.edu.kz.AkseleuMaksat.Service;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO createNews(NewsDTO newsDTO);
    List<NewsDTO> getAllNews();
    NewsDTO getNewsById(Long id);
    NewsDTO updateNews(Long id, NewsDTO newsDTO);
    boolean deleteNews(Long id);
    long deleteNewsByAuthorIdNull();

}