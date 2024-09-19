package com.themehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeInPurchaseDTO {
    private int id;
    private int quantity;
    private ThemeDTO theme;
}
