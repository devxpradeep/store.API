package com.rumble.store.controller.tools;

import com.rumble.store.model.common.ApiResponse;
import com.rumble.store.model.common.Metadata;
import com.rumble.store.model.request.CreateToolRequest;
import com.rumble.store.model.request.UpdateToolRequest;
import com.rumble.store.model.response.ToolResponse;
import com.rumble.store.services.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
@RequiredArgsConstructor
public class ToolController {

    private final ToolService toolService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ToolResponse>>> getAllTools(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        // Validate input parameters
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Page must be >= 0 and pageSize must be > 0");
        }

        // Fetch data and total count
        List<ToolResponse> tools = toolService.getAllTools(page, pageSize);
        long totalTools = toolService.getToolsTotal();

        // Calculate metadata
        int totalPages = (int) Math.ceil((double) totalTools / pageSize);

        // Build response
        Metadata metadata = Metadata.builder()
                .totalItems(totalTools)
                .pageSize(pageSize)
                .page(page)
                .totalPages(totalPages)
                .build();

        ApiResponse<List<ToolResponse>> response = ApiResponse.<List<ToolResponse>>builder()
                .data(tools)
                .success(true)
                .metadata(metadata)
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<ToolResponse>> createTool(
            @Valid @RequestBody CreateToolRequest createToolRequest
    ) {
        ApiResponse<ToolResponse> result = toolService.createTool(createToolRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/{toolId}")
    public ResponseEntity<ApiResponse<ToolResponse>> updateTool(
            @PathVariable String toolId,
            @Valid @RequestBody UpdateToolRequest updateToolRequest
    ) {
        ApiResponse<ToolResponse> result = toolService.updateTool(toolId, updateToolRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/{toolId}")
    public ResponseEntity<ApiResponse<ToolResponse>> getTool(
            @PathVariable String toolId
    ) {
        ApiResponse<ToolResponse> result = toolService.getTool(toolId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/{toolId}")
    public ResponseEntity<ApiResponse<ToolResponse>> deleteTool(
            @PathVariable String toolId
    ) {
        ApiResponse<ToolResponse> result = toolService.deleteTool(toolId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
