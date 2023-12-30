package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {


    TagDTO toDTO(Tag tag);

    Tag toEntity(TagDTO tagDTO);
}
