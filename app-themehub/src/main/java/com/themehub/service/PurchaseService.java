package com.themehub.service;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.PurchaseDTO;
import com.themehub.dto.ReviewDTO;
import com.themehub.dto.request.PurchaseDTORequest;
import com.themehub.dto.request.ReviewDTORequest;
import com.themehub.model.Purchase;
import com.themehub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PurchaseService {

    List<PurchaseDTO> getAllPurchases();
    List<PurchaseDTO> getPurchasesByUser(User user);
    List<PurchaseDTO> getPurchasesByUserInPeriod(User user, Date startDate, Date endDate);
    HrefEntityDTO addPurchase(PurchaseDTORequest purchase );

//    HrefEntityDTO save(PurchaseDTORequest dto );
//    HrefEntityDTO update( PurchaseDTORequest dto , Long id);
//    HrefEntityDTO delete( Long id);
//    PurchaseDTO searchById(Long id );


}
