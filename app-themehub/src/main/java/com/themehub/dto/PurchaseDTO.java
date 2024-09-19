package com.themehub.dto;

import com.themehub.model.Review;
import com.themehub.model.Theme;
import com.themehub.model.ThemeInPurchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private Long idPurchase;
    private ThemeDTO theme;
    private UserDTO buyer;
    private Date purchaseTime;
    private List<ThemeInPurchaseDTO> themesInPurchase;
}
