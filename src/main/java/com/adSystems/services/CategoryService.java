package com.adSystems.services;

import com.adSystems.datas.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getRootCategories();
    List<Category> getSubCategories(String rootCategoryId);
}
