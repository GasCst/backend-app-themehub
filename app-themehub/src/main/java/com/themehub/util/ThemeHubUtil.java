package com.themehub.util;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.PageableDTO;
import com.themehub.errorhandler.EntityGenericServerException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.lang.reflect.Field;
import java.util.Optional;

@Component
public class ThemeHubUtil {


    public static String preFormat(String ref_catena) {
        String ref_a = ref_catena.toLowerCase().replace('á', 'a');
        String ref_e = ref_a.toLowerCase().replace('é', 'e');
        String ref_i = ref_e.toLowerCase().replace('í', 'i');
        String ref_o = ref_i.toLowerCase().replace('ó', 'o');
        String ref_u = ref_o.toLowerCase().replace('ú', 'u');
        return ref_u;
    }

    public static String getEmail() {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authenticationToken.getCredentials();
        String email = (String) jwt.getClaims().get("email");
        return email;
    }


    public Pageable getPageable(PageableDTO pageableDTO) {
        Optional<Integer> sortOrder = pageableDTO.getOrder();
        Optional<String> sortField = pageableDTO.getField();
        Integer pageNumber = pageableDTO.getPageNumber();
        Integer perPage = pageableDTO.getPageSize();

        Pageable pageable;
        if (sortOrder.isPresent() && sortField.isPresent()) {
            Sort.Direction direction = sortOrder.get().equals(1) ? Sort.Direction.ASC : Sort.Direction.DESC;
            pageable = PageRequest.of(pageNumber, perPage, Sort.by(direction, sortField.get()));
        } else {
            pageable = PageRequest.of(pageNumber, perPage, Sort.by(Sort.Direction.DESC, "id"));
        }
        return pageable;
    }

    public HrefEntityDTO createHrefFromResource(Object id, ThemeHubResource resource)
            throws EntityGenericServerException {
        HrefEntityDTO hrefEntity = new HrefEntityDTO();
        try {
            StringBuilder builder = new StringBuilder();
            Field field = ThemehubConstant.class.getDeclaredField("RESOURCE_" + resource + "S");
            Object valueResource = field.get("");
            builder.append(valueResource);
            field = ThemehubConstant.class.getDeclaredField("RESOURCE_" + resource + "S_" + resource);
            valueResource = field.get("");
            builder.append(valueResource).append("/").append(id);
            hrefEntity.setId(id);
            hrefEntity.setHref(builder.toString());
        } catch (Exception e) {
            throw new EntityGenericServerException("Error generating href resource");
        }
        return hrefEntity;
    }
}
