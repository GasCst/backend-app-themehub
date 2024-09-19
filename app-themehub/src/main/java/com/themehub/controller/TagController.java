package com.themehub.controller;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.PageableDTO;
import com.themehub.dto.TagDTO;
import com.themehub.dto.ThemeDTO;
import com.themehub.dto.request.TagDTORequest;
import com.themehub.dto.request.ThemeDTORequest;
import com.themehub.service.TagService;
import com.themehub.util.ThemeHubUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class TagController {

    final TagService tagService;
    private ThemeHubUtil util;

    public TagController(TagService tagService, ThemeHubUtil util) {
        this.tagService = tagService;
        this.util = util;
    }


    @GetMapping(ThemehubConstant.RESOURCE_TAGS + ThemehubConstant.RESOURCE_TAGS_TAG + ThemehubConstant.RESOURCE_FINDBYNAME)
    public Page<TagDTO> findByName(@RequestParam String name, PageableDTO pageable) {
        log.info("crce TagController -> {} " + pageable);
        return this.tagService.findByName(name, this.util.getPageable(pageable));
    }

    @GetMapping(ThemehubConstant.RESOURCE_TAGS + ThemehubConstant.RESOURCE_TAGS_TAG + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<TagDTO>(this.tagService.findById(id), HttpStatus.OK);
    }


    @PostMapping(ThemehubConstant.RESOURCE_TAGS + ThemehubConstant.RESOURCE_TAGS_TAG)
    public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid TagDTORequest dto) {
        return new ResponseEntity<HrefEntityDTO>(this.tagService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping(ThemehubConstant.RESOURCE_TAGS + ThemehubConstant.RESOURCE_TAGS_TAG + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> update(@RequestBody TagDTORequest dto, @PathVariable Long id) {
        return new ResponseEntity<HrefEntityDTO>(this.tagService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(ThemehubConstant.RESOURCE_TAGS + ThemehubConstant.RESOURCE_TAGS_TAG + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
        return new ResponseEntity<HrefEntityDTO>(this.tagService.delete(id), HttpStatus.OK);
    }

}
