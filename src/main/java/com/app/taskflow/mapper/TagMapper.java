package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    
    Tag toEntity(TagDTO tagDTO);

    TagDTO toDTO(Tag tag);
}
