package com.rumble.store.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Metadata {
    private int page;
    private int pageSize;
    private long totalItems;
    private int totalPages;
}
