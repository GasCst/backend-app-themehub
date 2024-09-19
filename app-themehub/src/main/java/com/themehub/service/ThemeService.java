package com.themehub.service;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.ThemeDTO;
import com.themehub.dto.request.ThemeDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ThemeService {

    public List<ThemeDTO> getAllThemes();

    public ThemeDTO getThemeById(Long idTheme);

    public HrefEntityDTO save(ThemeDTORequest dto);

    public HrefEntityDTO delete(Long id);

    public HrefEntityDTO update(ThemeDTORequest dto,Long id);

    public Page<ThemeDTO> findByNameLike( String name, Pageable pePageable);

}
