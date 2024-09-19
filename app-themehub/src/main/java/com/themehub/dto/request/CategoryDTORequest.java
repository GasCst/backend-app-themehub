package com.themehub.dto.request;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDTORequest {
    private String name;
    private String description;
}
