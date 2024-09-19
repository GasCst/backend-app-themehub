package com.themehub.mapper;


import com.themehub.dto.UserDTO;
import com.themehub.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(builder = @Builder(disableBuilder = true))
@Component // or @Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "first_name", source ="firstName")
    @Mapping(target = "last_name", source ="lastName")
    public UserDTO toDTO (User user );

}
