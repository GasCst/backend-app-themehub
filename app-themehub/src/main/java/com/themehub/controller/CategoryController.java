package com.themehub.controller;
import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.CategoryDTO;
import com.themehub.dto.HrefEntityDTOCategory;
import com.themehub.dto.request.CategoryDTORequest;
import com.themehub.model.Category;
import com.themehub.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(ThemehubConstant.RESOURCE_CATEGORIES + ThemehubConstant.RESOURCE_CATEGORIES_CATEGORY)
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(ThemehubConstant.RESOURCE_CATEGORIES + ThemehubConstant.RESOURCE_CATEGORIES_CATEGORY + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            return ResponseEntity.ok(categoryDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    @GetMapping("/categories")
//    public List<CategoryDTO>
//    findCategoriesByNameContaining(@RequestParam(value = "description", required = false) String description) {
//        return categoryService.findCategoriesByNameContaining(description);
//    }
//
//    @GetMapping("/category/{id}")
//    public Category getCategoryById(@PathVariable Long id) {
//        return categoryService.getCategoryById(id);
//    }
//
//    @GetMapping("/category/exists/{id}")
//    public boolean existsCategoryById(@PathVariable Long id) {
//        return categoryService.existsCategoryById(id);
//    }


    @PostMapping(ThemehubConstant.RESOURCE_CATEGORIES + ThemehubConstant.RESOURCE_CATEGORIES_CATEGORY)
    public ResponseEntity<HrefEntityDTOCategory> createCategory(@RequestBody CategoryDTORequest dto) {
        HrefEntityDTOCategory href = categoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(href);
    }

    @PutMapping(ThemehubConstant.RESOURCE_CATEGORIES + ThemehubConstant.RESOURCE_CATEGORIES_CATEGORY + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTOCategory> updateCategory(@PathVariable Long id, @RequestBody CategoryDTORequest dto) {
        try {
            HrefEntityDTOCategory href = categoryService.update(dto, id);
            return ResponseEntity.ok(href);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(ThemehubConstant.RESOURCE_CATEGORIES + ThemehubConstant.RESOURCE_CATEGORIES_CATEGORY + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTOCategory> deleteCategory(@PathVariable Long id) {
        try {
            HrefEntityDTOCategory href = categoryService.delete(id);
            return ResponseEntity.ok(href);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}



