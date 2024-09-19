package com.themehub.dto.request;

import com.themehub.model.Review;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDTORequest implements Serializable {

    // Not required if idtheme is auto-generated in the database
    // private Long idtheme;

    @NotNull
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String preview_image;

    @NotEmpty
    private String demo_url;

    @NotEmpty
    private String category;

    @NotNull
    private BigDecimal price;

    @NotEmpty
    private String author;

    @NotEmpty
    private String author_profile;

    @NotEmpty
    private String download_link;

    @NotNull
    private String state;

    private List<Review> reviewList;
}
