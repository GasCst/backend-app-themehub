package com.themehub.service.impl;

import com.themehub.dto.*;
import com.themehub.dto.request.PurchaseDTORequest;
import com.themehub.dto.request.ReviewDTORequest;
import com.themehub.mapper.ReviewMapper;
import com.themehub.model.*;
import com.themehub.repository.*;
import com.themehub.service.PurchaseService;
import com.themehub.util.ThemeHubResource;
import com.themehub.util.ThemeHubUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    final PurchaseRepository purchaseRepository;
    final ThemeInPurchaseRepository themeInPurchaseRepository;
    final UserInterface userRepository;
    final EntityManager entityManager;
    final ThemeRepository themeRepository;
    final ThemeHubUtil util;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ThemeInPurchaseRepository themeInPurchaseRepository, UserInterface userRepository, EntityManager entityManager, ThemeRepository themeRepository, ThemeHubUtil util) {
        this.purchaseRepository = purchaseRepository;
        this.themeInPurchaseRepository = themeInPurchaseRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.themeRepository = themeRepository;
        this.util = util;
    }


    @Transactional(readOnly = true)
    @Override
    public List<PurchaseDTO> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public List<PurchaseDTO> getPurchasesByUser(User user) throws EntityNotFoundException {
        if (! (userRepository.existsById(user.getId())) ) {
            throw new EntityNotFoundException();
        }
        List<Purchase> purchases = purchaseRepository.findByBuyer(user);
        return purchases.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public List<PurchaseDTO> getPurchasesByUserInPeriod(User user, Date startDate, Date endDate) throws EntityNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException();
        }
        if (startDate.compareTo(endDate) >= 0) {
            throw new EntityNotFoundException();
        }
        List<Purchase> purchases = purchaseRepository.findByBuyerInPeriod(startDate, endDate, user);
        return purchases.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = false)
    @Override
    public HrefEntityDTO addPurchase(PurchaseDTORequest dto) {

        Purchase purchase = new Purchase();
        purchase.setPurchaseTime(new Date());

        Theme theme = themeRepository.findById(dto.getThemeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
        User buyer = userRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        purchase.setBuyer(buyer);
        purchase.setTheme(theme);

        List<ThemeInPurchase> themeInPurchases = dto.getThemesInPurchase().stream()
                .map(this::convertToThemeInPurchase)
                .collect(Collectors.toList());
        purchase.setThemesInPurchase(themeInPurchases);

        Purchase result = purchaseRepository.save(purchase);
        for (ThemeInPurchase tip : result.getThemesInPurchase()) {
            tip.setPurchase(result);
            themeInPurchaseRepository.save(tip);
        }

        PurchaseDTO purchaseDTO = convertToDTO(result);
        return util.createHrefFromResource(purchaseDTO.getIdPurchase(), ThemeHubResource.PURCHASE);
    }















    private List<ThemeInPurchase> convertToThemeInPurchase(List<ThemeInPurchaseDTO> dtos) {
        return dtos.stream().map(dto -> {
            ThemeInPurchase themeInPurchase = new ThemeInPurchase();
            themeInPurchase.setId(dto.getId());
            themeInPurchase.setQuantity(dto.getQuantity());
            Theme theme = themeRepository.findById(dto.getTheme().getIdtheme())
                    .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + dto.getTheme().getIdtheme()));
            themeInPurchase.setTheme(theme);

            return themeInPurchase;
        }).collect(Collectors.toList());
    }


    private PurchaseDTO convertToDTO(Purchase purchase) {
        return PurchaseDTO.builder()
                .idPurchase(purchase.getIdPurchase())
                .purchaseTime(purchase.getPurchaseTime())
                .buyer(convertUserToDTO(purchase.getBuyer()))
                .themesInPurchase(purchase.getThemesInPurchase().stream()
                        .map(this::convertThemeInPurchaseToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private ThemeInPurchaseDTO convertThemeInPurchaseToDTO(ThemeInPurchase tip) {
        return ThemeInPurchaseDTO.builder()
                .id(tip.getId())
                .quantity(tip.getQuantity())
                .theme(convertThemeToDTO(tip.getTheme()))  // Conversione da Theme a ThemeDTO
                .build();
    }

    private UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .company(user.getCompany())
                .state(user.getState())
                .build();
    }

    private ThemeDTO convertThemeToDTO(Theme theme) {
        return ThemeDTO.builder()
                .idtheme(theme.getIdtheme())
                .name(theme.getName())
                .description(theme.getDescription())
                .preview_image(theme.getPreviewImage())
                .demo_url(theme.getDemoUrl())
                .category(theme.getCategory())
                .price(theme.getPrice())
                .author(theme.getAuthor())
                .author_profile(theme.getAuthorProfile())
                .download_link(theme.getDownloadLink())
                .state(theme.getState())
                .reviewList(theme.getReviews())
                .build();
    }

    private ThemeInPurchase convertToThemeInPurchase(ThemeInPurchaseDTO dto) {
        // Create a new ThemeInPurchase entity
        ThemeInPurchase tip = new ThemeInPurchase();
        tip.setQuantity(dto.getQuantity());

        // Find the Theme from the repository and set it
        Theme theme = themeRepository.findById(dto.getTheme().getIdtheme())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
        tip.setTheme(theme);

        return tip;
    }

}
