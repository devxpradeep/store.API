package com.rumble.store.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ApiError {
    private String message;
    private List<String> details;
}

