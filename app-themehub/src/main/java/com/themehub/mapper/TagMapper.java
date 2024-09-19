package com.themehub.mapper;



import com.themehub.dto.TagDTO;
import com.themehub.dto.request.TagDTORequest;
import com.themehub.model.Tag;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(builder = @Builder(disableBuilder = true))
@Component // or @Mapper(componentModel = "spring")
public interface TagMapper {


     Tag toBean( TagDTORequest dto );
     TagDTO toDTO( Tag tag );

}
