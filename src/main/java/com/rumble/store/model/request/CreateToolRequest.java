package com.rumble.store.model.request;

import lombok.Data;

@Data
public class CreateToolRequest {
    private String name;
    private String description;
    private String createdBy;
    private Boolean isActive;
    private String bgImageUrl;
    private String avatarUrl;
}
