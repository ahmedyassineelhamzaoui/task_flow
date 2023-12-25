package com.app.taskflow.mapper;


import com.app.taskflow.models.dto.TagDTO;
import com.app.taskflow.models.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class TagMapper {

    private final ModelMapper modelMapper;

    public Tag mapToTag(TagDTO tagDTO){
        return modelMapper.map(tagDTO,Tag.class);
    }
    public TagDTO mapToDTO(Tag tag){
        return modelMapper.map(tag,TagDTO.class);
    }
}
