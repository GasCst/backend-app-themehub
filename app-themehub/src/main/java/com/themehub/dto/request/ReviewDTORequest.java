package com.themehub.dto.request;

import com.themehub.dto.ThemeDTO;
import com.themehub.dto.UserDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTORequest {

    @NotNull
    private int rating;

    @NotNull
    private String comment;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long idUser;

    @NotNull
    private Long idTheme;

}
