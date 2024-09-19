package com.themehub.controller;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.PageableDTO;
import com.themehub.dto.ThemeDTO;
import com.themehub.dto.request.ThemeDTORequest;
import com.themehub.model.Theme;
import com.themehub.util.ThemeHubUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.themehub.constant.ThemehubConstant;
import com.themehub.service.ThemeService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class ThemeController {

    private
    ThemeService themeService;

    final
    ThemeHubUtil util;

    public ThemeController(ThemeService themeService, ThemeHubUtil util) {
        this.themeService = themeService;
        this.util = util;
    }

    @GetMapping(ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME)
    public List<ThemeDTO> getAllThemes(){
        return this.themeService.getAllThemes();
    }

    @GetMapping(ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ThemeDTO getThemeById(@PathVariable Long id) {
        return this.themeService.getThemeById(id);
    }


    @GetMapping( ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME + ThemehubConstant.RESOURCE_FINDBYNAME )
    public Page<ThemeDTO> findByName (@RequestParam String name, PageableDTO pageable ){
        log.info("crce ThemeController -> {} "+ pageable );
        return this.themeService.findByNameLike(name, this.util.getPageable(pageable));
    }


    @PostMapping(ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME)
    public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid ThemeDTORequest dto) {
        return new ResponseEntity<HrefEntityDTO>(this.themeService.save(dto), HttpStatus.CREATED);
    }
    
    @PutMapping(ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> update(@RequestBody ThemeDTORequest dto,@PathVariable Long id) {
        return new ResponseEntity<HrefEntityDTO>(this.themeService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(ThemehubConstant.RESOURCE_THEMES + ThemehubConstant.RESOURCE_THEMES_THEME + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
        log.info("controller delete -> {} "+id);
        return new ResponseEntity<HrefEntityDTO>(this.themeService.delete(id), HttpStatus.OK);
    }
}
