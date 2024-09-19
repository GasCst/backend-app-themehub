package com.themehub.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagDTORequest {

    @NotNull
    @NotEmpty
    @Size(min = 1 ,max = 255)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1 ,max = 50)
    private String state;


}
