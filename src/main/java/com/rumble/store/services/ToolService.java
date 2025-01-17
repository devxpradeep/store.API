package com.rumble.store.services;

import com.rumble.store.model.common.ApiResponse;
import com.rumble.store.model.request.CreateToolRequest;
import com.rumble.store.model.request.UpdateToolRequest;
import com.rumble.store.model.response.ToolResponse;

import java.util.List;

public interface ToolService {
    List<ToolResponse> getAllTools(int page, int pageSize);
    long getToolsTotal();
    ApiResponse<ToolResponse> createTool(CreateToolRequest createToolRequest);
    ApiResponse<ToolResponse> getTool(String toolId);
    ApiResponse<ToolResponse> updateTool(String toolId, UpdateToolRequest updateToolRequest);
    ApiResponse<ToolResponse> deleteTool(String toolId);
}
