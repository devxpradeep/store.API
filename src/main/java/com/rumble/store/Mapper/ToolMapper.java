package com.rumble.store.Mapper;


import com.rumble.store.entity.Tool;
import com.rumble.store.model.request.CreateToolRequest;
import com.rumble.store.model.request.UpdateToolRequest;
import com.rumble.store.model.response.ToolResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ToolMapper {
    @Mapping(source = "id", target = "toolId")
    ToolResponse toDto(Tool tool);
    Tool toEntity(CreateToolRequest createToolRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Tool updateEntityFromDto(UpdateToolRequest updateToolRequest, @MappingTarget Tool tool);
}
