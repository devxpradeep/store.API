package com.rumble.store.services.impl;

import com.rumble.store.Mapper.ToolMapper;
import com.rumble.store.builders.CustomResponseBuilder;
import com.rumble.store.entity.Tool;
import com.rumble.store.model.common.ApiResponse;
import com.rumble.store.model.request.CreateToolRequest;
import com.rumble.store.model.request.UpdateToolRequest;
import com.rumble.store.model.response.ToolResponse;
import com.rumble.store.repository.ToolRepository;
import com.rumble.store.services.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {
    private final ToolRepository toolRepository;
    private final ToolMapper toolMapper;
    private final CustomResponseBuilder responseBuilder;

    @Override
    public List<ToolResponse> getAllTools(int page, int pageSize) {
        return toolRepository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(toolMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long getToolsTotal() {
        return toolRepository.count();
    }

    @Override
    public ApiResponse<ToolResponse> createTool(CreateToolRequest createToolRequest) {
        try {
            Tool tool = toolMapper.toEntity(createToolRequest);
            tool = toolRepository.save(tool);
            ToolResponse toolResponse = toolMapper.toDto(tool);
            return responseBuilder.buildSuccessResponse(toolResponse);
        } catch (Exception ex) {
            //log.error("Error creating tool: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,List.of(ex.getMessage()));
        }
    }


    @Override
    public ApiResponse<ToolResponse> getTool(String toolId) {
        try{
            Optional<Tool> toolFromDb = toolRepository.findById(toolId);
            if(toolFromDb.isPresent()){
                Tool tool = toolFromDb.get();
                ToolResponse toolResponse = toolMapper.toDto(tool);
                return responseBuilder.buildSuccessResponse(toolResponse);
            }
            else{
                return responseBuilder.buildNotFoundResponse();
            }
        }
        catch(Exception ex){
            //log.error("Error creating tool: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<ToolResponse> updateTool(String toolId,UpdateToolRequest updateToolRequest) {
        try {
            Optional<Tool> toolFromDb = toolRepository.findById(toolId);
            if(toolFromDb.isPresent()){
                Tool tool = toolFromDb.get();
                Tool updated = toolMapper.updateEntityFromDto(updateToolRequest,tool);
                toolRepository.save(updated);
                ToolResponse toolResponse = toolMapper.toDto(updated);
                return responseBuilder.buildSuccessResponse(toolResponse);
            }
            else{
                return responseBuilder.buildNotFoundResponse();
            }
        } catch (Exception ex) {
            //log.error("Error creating tool: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<ToolResponse> deleteTool(String toolId) {
        try{
            Optional<Tool> toolFromDb = toolRepository.findById(toolId);
            if(toolFromDb.isPresent()){
                Tool tool = toolFromDb.get();
                toolRepository.deleteById(toolId);
                ToolResponse toolResponse = toolMapper.toDto(tool);
                return responseBuilder.buildSuccessResponse(toolResponse);
            }
            else{
                return responseBuilder.buildNotFoundResponse();
            }
        }
        catch(Exception ex){
            //log.error("Error creating tool: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,List.of(ex.getMessage()));
        }
    }
}
