package iitu.edu.kz.AkseleuMaksat.Service.impl;

import iitu.edu.kz.AkseleuMaksat.DTO.TagDTO;
import iitu.edu.kz.AkseleuMaksat.Entity.Tag;
import iitu.edu.kz.AkseleuMaksat.Repository.TagRepository;
import iitu.edu.kz.AkseleuMaksat.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        return mapToDTO(tagRepository.save(tag));
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Long id) {
        return tagRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Tag existingTag = tagRepository.findById(id).orElse(null);
        if (existingTag != null) {
            existingTag.setName(tagDTO.getName());
            return mapToDTO(tagRepository.save(existingTag));
        }
        return null;
    }

    @Override
    public boolean deleteTag(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private TagDTO mapToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }
}