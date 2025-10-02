package com.haako.blog.services.impl;

import com.haako.blog.domain.entities.Category;
import com.haako.blog.repositories.CategoryRepository;
import com.haako.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
           throw new IllegalArgumentException("Category already exists with name: "+ categoryName);
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalArgumentException("Category has posts associated with it");
            }
           categoryRepository.deleteById(id);
        }
    }
}
