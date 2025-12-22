package com.adSystems.services;

import com.adSystems.datas.models.Category;
import com.adSystems.datas.repositories.CategoryRepository;
import com.adSystems.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImplementation implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getRootCategories() {
        return categoryRepository.findParentIdIsNull();
    }

    @Override
    public List<Category> getSubCategories(String rootCategoryId) {
        if(rootCategoryId == null || rootCategoryId.trim().isEmpty()) throw new ValidationException("Parent id cannot be empty");
        return categoryRepository.findByParentId(rootCategoryId);
    }
}
