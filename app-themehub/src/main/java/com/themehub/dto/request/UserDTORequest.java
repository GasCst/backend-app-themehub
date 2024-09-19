package com.themehub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORequest implements Serializable {

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 10, max = 100)
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    private String first_name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String last_name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String company;

    @NotNull
    @NotEmpty
    @NotBlank
    private String state;

}
