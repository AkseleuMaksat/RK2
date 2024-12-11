package iitu.edu.kz.AkseleuMaksat.Service;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsTagDTO;
import java.util.List;

public interface NewsTagService {
    NewsTagDTO createNewsTag(NewsTagDTO newsTagDTO);
    List<NewsTagDTO> getAllNewsTags();
    boolean deleteNewsTag(Long id);
}