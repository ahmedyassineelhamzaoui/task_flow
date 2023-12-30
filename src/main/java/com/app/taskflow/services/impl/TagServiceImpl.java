package com.app.taskflow.services.impl;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.services.facade.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepsoitory tagRepsoitory;
    @Override
    public Optional<TagDTO> getTagById(UUID tagId) {
        return Optional.empty();
    }

    @Override
    public TagDTO addTag(TagDTO tagDTO) {
        return null;
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
