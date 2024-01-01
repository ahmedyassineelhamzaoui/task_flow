package com.app.taskflow.mapper;

import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.models.entity.Demand;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface DemandMapper {
    DemandDTO toDTO(Demand demand);

    Demand toEntity(DemandDTO demandDTO);
}
