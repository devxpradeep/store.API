package com.rumble.store.model.response;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class ToolResponse {
    private String toolId;
    private String name;
    private String description;
    private String createdBy;
    private String modifiedBy;
    private Boolean isActive;
    private String bgImageUrl;
    private String avatarUrl;
}
