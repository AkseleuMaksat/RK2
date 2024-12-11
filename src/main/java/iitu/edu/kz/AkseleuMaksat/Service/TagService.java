package iitu.edu.kz.AkseleuMaksat.Service;

import iitu.edu.kz.AkseleuMaksat.DTO.TagDTO;
import java.util.List;

public interface TagService {
    TagDTO createTag(TagDTO tagDTO);
    List<TagDTO> getAllTags();
    TagDTO getTagById(Long id);
    TagDTO updateTag(Long id, TagDTO tagDTO);
    boolean deleteTag(Long id);
}