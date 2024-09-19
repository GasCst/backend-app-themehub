package com.themehub.mapper;

import com.themehub.dto.ReviewDTO;
import com.themehub.dto.request.ReviewDTORequest;
import com.themehub.model.Review;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(builder = @Builder(disableBuilder = true))
@Component // or @Mapper(componentModel = "spring")
public interface ReviewMapper {

     public Review toBean(ReviewDTORequest dto );

     public ReviewDTO toDTO(Review Review );

}
