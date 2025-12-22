package com.adSystems.dtos.requests;


import lombok.Data;

@Data
public class GetByCityAndCategoryRequest {
    private String cityId;
    private String categoryId;
}
