package com.app.taskflow.services.impl;

import com.app.taskflow.mapper.TagMapper;
import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.entity.Tag;
import com.app.taskflow.repositories.TagRepository;
import com.app.taskflow.services.facade.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    @Override
    public Optional<TagDTO> getTagById(UUID tagId) {
        return Optional.empty();
    }

    @Override
    public TagDTO addTag(TagDTO tagDTO) {
        Tag tag = tagMapper.toEntity(tagDTO);
        TagDTO tagDTOResponse = tagMapper.toDTO(tagRepository.save(tag));
        return tagDTOResponse;
    }

    @Override
    public List<TagDTO> getAllTags() {
        return null;
    }

    @Override
    public void deleteTag(UUID tagId) {

    }

    @Override
    public TagDTO updateTag(UUID tagId, TagDTO tagDTO) {
        return null;
    }
}
