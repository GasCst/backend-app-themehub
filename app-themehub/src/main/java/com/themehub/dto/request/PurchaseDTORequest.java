package com.themehub.dto.request;

import com.themehub.dto.ThemeDTO;
import com.themehub.dto.ThemeInPurchaseDTO;
import com.themehub.dto.UserDTO;
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
public class PurchaseDTORequest {


    private Long themeId;
    private Long buyerId;
    private List<ThemeInPurchaseDTO> themesInPurchase;


}
