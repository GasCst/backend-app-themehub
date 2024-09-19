package com.themehub.service;

import com.themehub.dto.CategoryDTO;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.HrefEntityDTOCategory;
import com.themehub.dto.request.CategoryDTORequest;
import com.themehub.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    HrefEntityDTOCategory save(CategoryDTORequest dto);

    HrefEntityDTOCategory delete(Long id);

    HrefEntityDTOCategory update(CategoryDTORequest dto, Long id);

    List<CategoryDTO> findCategoriesByNameContaining(String description);

    Category getCategoryByIdJPSQL(Long id);

    boolean existsCategoryById(Long id);
}
