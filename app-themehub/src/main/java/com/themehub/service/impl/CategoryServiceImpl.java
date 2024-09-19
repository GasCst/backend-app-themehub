package com.themehub.service.impl;

import com.themehub.dto.CategoryDTO;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.HrefEntityDTOCategory;
import com.themehub.mapper.CategoryMapper;
import com.themehub.util.ThemeHubUtil;
import com.themehub.dto.request.CategoryDTORequest;
import com.themehub.model.Category;
import com.themehub.repository.CategoryRepository;
import com.themehub.service.CategoryService;
import com.themehub.util.ThemeHubResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    final ThemeHubUtil util;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,ThemeHubUtil util) {
        this.categoryRepository = categoryRepository;
        this.util = util;
    }

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Override
    public HrefEntityDTOCategory save(CategoryDTORequest dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return new HrefEntityDTOCategory("/api/categories/" + savedCategory.getId());
    }

    @Override
    public HrefEntityDTOCategory delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new HrefEntityDTOCategory("/api/categories");
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    @Override
    public HrefEntityDTOCategory update(CategoryDTORequest dto, Long id) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(dto.getName());
                    existingCategory.setDescription(dto.getDescription());
                    Category updatedCategory = categoryRepository.save(existingCategory);
                    return new HrefEntityDTOCategory("/api/categories/" + updatedCategory.getId());
                })
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    private CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryDTO> findCategoriesByNameContaining(String description) {
        List<Category> categories = categoryRepository.findByDescriptionContainingOrderByIdAsc(description);
        return categoryMapper.toDTOs(categories);
    }

    @Override
    public Category getCategoryByIdJPSQL(Long id) {
        return categoryRepository.findByIdJPSQL(id);
    }

    @Override
    public boolean existsCategoryById(Long id) {
        return categoryRepository.existsByIdSQL(id);
    }
}










