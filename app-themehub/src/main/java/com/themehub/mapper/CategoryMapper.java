package com.themehub.mapper;

import com.themehub.dto.CategoryDTO;
import com.themehub.dto.ThemeDTO;
import com.themehub.model.Category;
import com.themehub.model.Theme;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@Component // or @Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    List<CategoryDTO> toDTOs( List<Category> categories );

}
