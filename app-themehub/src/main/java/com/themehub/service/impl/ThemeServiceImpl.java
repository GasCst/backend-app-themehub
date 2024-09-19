package com.themehub.service.impl;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.ThemeDTO;
import com.themehub.dto.request.ThemeDTORequest;
import com.themehub.errorhandler.EntityNotFoundException;
import com.themehub.mapper.ThemeMapper;
import com.themehub.model.Theme;
import com.themehub.service.ThemeService;
import com.themehub.util.ThemeHubResource;
import com.themehub.util.ThemeHubUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.themehub.repository.ThemeRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ThemeServiceImpl implements ThemeService {

    ThemeRepository themeRepository;
    final ThemeHubUtil util;

    final ThemeMapper themeMapper;

    public ThemeServiceImpl(ThemeRepository themeRepository, ThemeHubUtil util, ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.util = util;
        this.themeMapper = themeMapper;
    }

    @Override
    public List<ThemeDTO> getAllThemes() {
        List<Theme> list = this.themeRepository.findAll();
        List<ThemeDTO> listDTOs = new ArrayList<>();
        list.forEach((bean)->{
            ThemeDTO dto = new ThemeDTO();
            dto.setIdtheme(bean.getIdtheme());
            dto.setName(bean.getName());
            dto.setAuthor(bean.getAuthor());
            dto.setState(bean.getState());
            dto.setCategory(bean.getCategory());
            dto.setDescription(bean.getDescription());
            dto.setPrice(bean.getPrice());
            dto.setAuthor_profile(bean.getAuthorProfile());
            dto.setDownload_link(bean.getDownloadLink());
            dto.setPreview_image(bean.getPreviewImage());
            dto.setDemo_url(bean.getDemoUrl());
            dto.setReviewList(bean.getReviews());
            listDTOs.add(dto);
        });
        return listDTOs;
    }

    @Override
    public ThemeDTO getThemeById(Long idTheme) {
        Theme theme = this.themeRepository.findById(idTheme)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Il tema con id %s non esiste", idTheme.toString())));
        ThemeDTO dto = new ThemeDTO();
        dto.setIdtheme(theme.getIdtheme());
        dto.setName(theme.getName());
        dto.setAuthor(theme.getAuthor());
        dto.setState(theme.getState());
        dto.setCategory(theme.getCategory());
        dto.setDescription(theme.getDescription());
        dto.setPrice(theme.getPrice());
        dto.setAuthor_profile(theme.getAuthorProfile());
        dto.setDownload_link(theme.getDownloadLink());
        dto.setPreview_image(theme.getPreviewImage());
        dto.setDemo_url(theme.getDemoUrl());
        dto.setReviewList(theme.getReviews());
        return dto;
    }

    @Override
    public HrefEntityDTO save(ThemeDTORequest dto) {
        Theme theme = new Theme();
        theme.setName(dto.getName());
        theme.setAuthor(dto.getAuthor());
        theme.setAuthorProfile(dto.getAuthor_profile());
        theme.setDemoUrl(dto.getDemo_url());
        theme.setDownloadLink(dto.getDownload_link());
        theme.setPrice(dto.getPrice());
        theme.setCategory(dto.getCategory());
        theme.setDescription(dto.getDescription());
        theme.setPreviewImage(dto.getPreview_image());
        theme.setState(dto.getState());
        theme.setReviews(dto.getReviewList());

        return util.createHrefFromResource(this.themeRepository.save(theme).getIdtheme(),
                ThemeHubResource.THEME);
    }


    @Override
    public HrefEntityDTO update(ThemeDTORequest dto, Long id) {
        Theme theme = this.themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("theme not found"));
        theme.setName(dto.getName());
        theme.setAuthor(dto.getAuthor());
        theme.setAuthorProfile(dto.getAuthor_profile());
        theme.setDemoUrl(dto.getDemo_url());
        theme.setDownloadLink(dto.getDownload_link());
        theme.setPrice(dto.getPrice());
        theme.setCategory(dto.getCategory());
        theme.setDescription(dto.getDescription());
        theme.setPreviewImage(dto.getPreview_image());
        theme.setState(dto.getState());
        theme.setReviews(dto.getReviewList());
        return util.createHrefFromResource(this.themeRepository.save(theme).getIdtheme(),
                ThemeHubResource.THEME);
    }

    @Override
    public Page<ThemeDTO> findByNameLike(String name, Pageable pePageable) {
        Page<Theme> themePages = this.themeRepository.findByNameLikeAndState("%"+name+"%",
                "active", pePageable);
        return themePages.map(theme -> {
            ThemeDTO dto = new ThemeDTO();
            dto.setIdtheme(theme.getIdtheme());
            dto.setName(theme.getName());
            dto.setDescription(theme.getDescription());
            dto.setPreview_image(theme.getPreviewImage());
            dto.setDemo_url(theme.getDemoUrl());
            dto.setCategory(theme.getCategory());
            dto.setPrice(theme.getPrice());
            dto.setAuthor(theme.getAuthor());
            dto.setAuthor_profile(theme.getAuthorProfile());
            dto.setDownload_link(theme.getDownloadLink());
            dto.setState(theme.getState());
            dto.setReviewList(theme.getReviews());
            return dto;
        });
    }

    @Override
    public HrefEntityDTO delete(Long id) {
        Theme theme = this.themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("theme not found"));

        this.themeRepository.deleteById(id);

        return util.createHrefFromResource(id, ThemeHubResource.THEME);
    }


    public ThemeDTO convertBeanToDto( Theme theme ){
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

}





