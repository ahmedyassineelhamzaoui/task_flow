package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.entity.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public  interface TagService {

    Optional<TagDTO> getTagById(UUID tagId);

    TagDTO addTag(TagDTO tagDTO);

    List<TagDTO> getAllTags();

    void deleteTag(UUID tagId);

    TagDTO updateTag(UUID tagId, TagDTO tagDTO);
}
