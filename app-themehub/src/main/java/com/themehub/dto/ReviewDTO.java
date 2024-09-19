package com.themehub.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long idReview;
    private int rating;
    private String comment;
    private LocalDateTime date;
    private UserDTO user;
    private ThemeDTO theme;

}
