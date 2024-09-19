package com.themehub.mapper;

import com.themehub.dto.ThemeDTO;
import com.themehub.model.Theme;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(builder = @Builder(disableBuilder = true))
@Component // or @Mapper(componentModel = "spring")
public interface ThemeMapper {

    public ThemeDTO toDTO ( Theme theme );

}
