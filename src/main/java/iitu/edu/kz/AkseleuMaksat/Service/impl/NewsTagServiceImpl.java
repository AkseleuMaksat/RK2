package iitu.edu.kz.AkseleuMaksat.Service.impl;

import iitu.edu.kz.AkseleuMaksat.DTO.NewsTagDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.NewsTag;
import iitu.edu.kz.AkseleuMaksat.Entity.News;
import iitu.edu.kz.AkseleuMaksat.Entity.Tag;
import iitu.edu.kz.AkseleuMaksat.Repository.NewsTagRepository;
import iitu.edu.kz.AkseleuMaksat.Repository.NewsRepository;
import iitu.edu.kz.AkseleuMaksat.Repository.TagRepository;
import iitu.edu.kz.AkseleuMaksat.Service.NewsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsTagServiceImpl implements NewsTagService {

    private final NewsTagRepository newsTagRepository;
    private final NewsRepository newsRepository;
    private final TagRepository tagRepository;

    @Autowired
    public NewsTagServiceImpl(NewsTagRepository newsTagRepository, NewsRepository newsRepository, TagRepository tagRepository) {
        this.newsTagRepository = newsTagRepository;
        this.newsRepository = newsRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public NewsTagDTO createNewsTag(NewsTagDTO newsTagDTO) {
        // Find News and Tag entities by their IDs
        News news = newsRepository.findById(newsTagDTO.getNewsId()).orElseThrow(() -> new RuntimeException("News not found"));
        Tag tag = tagRepository.findById(newsTagDTO.getTagId()).orElseThrow(() -> new RuntimeException("Tag not found"));

        // Create a new NewsTag entity
        NewsTag newsTag = new NewsTag();
        newsTag.setNews(news);
        newsTag.setTag(tag);

        return mapToDTO(newsTagRepository.save(newsTag));
    }

    @Override
    public List<NewsTagDTO> getAllNewsTags() {
        return newsTagRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteNewsTag(Long id) {
        if (newsTagRepository.existsById(id)) {
            newsTagRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private NewsTagDTO mapToDTO(NewsTag newsTag) {
        NewsTagDTO dto = new NewsTagDTO();
        dto.setId(newsTag.getId());
        dto.setNewsId(newsTag.getNews().getId()); // Getting the ID of News
        dto.setTagId(newsTag.getTag().getId());   // Getting the ID of Tag
        return dto;
    }
}