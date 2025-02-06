package com.rumble.store.model.request;

import lombok.Data;

@Data
public class UpdateToolRequest {
    private String name;
    private String description;
    private String modifiedBy;
    private Boolean isActive;
    private String bgImageUrl;
    private String avatarUrl;
}
