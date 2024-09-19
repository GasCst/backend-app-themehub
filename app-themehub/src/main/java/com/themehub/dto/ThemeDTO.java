package com.themehub.dto;

import com.themehub.model.Review;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDTO implements Serializable{

    private Long idtheme;
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String preview_image;

    @NotEmpty
    private String demo_url;

    @NotEmpty
    private String category;

    @NotEmpty
    private BigDecimal price;

    @NotEmpty
    private String author;

    @NotEmpty
    private String author_profile;

    @NotEmpty
    private String download_link;

    @NotEmpty
    private String state;

    private List<Review> reviewList;

}
