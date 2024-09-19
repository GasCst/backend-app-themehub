package com.themehub.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Long idtag ;
    private String name;
    private String state;

}
