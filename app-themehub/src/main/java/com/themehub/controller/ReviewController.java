package com.themehub.controller;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.*;
import com.themehub.dto.request.ReviewDTORequest;
import com.themehub.service.ReviewService;
import com.themehub.util.ThemeHubUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(ThemehubConstant.RESOURCE_GENERIC)
public class ReviewController {

    final ReviewService reviewService;
    private final ThemeHubUtil util;

    public ReviewController(ReviewService reviewService, ThemeHubUtil util) {
        this.reviewService = reviewService;
        this.util = util;
    }

    @GetMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW + "/findByRatingJPQL" )
    public ResponseEntity<Page<ReviewDTO>> findByRatingJPQL(@RequestParam Long rating, PageableDTO pageable ) {
        return new ResponseEntity<Page<ReviewDTO>>(this.reviewService.findByRatingJPQL(rating, this.util.getPageable(pageable)), HttpStatus.OK);
    }

    @GetMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW + "/findByKeyWordJPQL" )
    public ResponseEntity<Page<ReviewDTO>> findByKeyWordJPQL(@RequestParam String key_word, PageableDTO pageable ) {
        return new ResponseEntity<Page<ReviewDTO>>(this.reviewService.findByKeyWordJPQL(key_word, this.util.getPageable(pageable)), HttpStatus.OK);
    }



    @GetMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<ReviewDTO>(this.reviewService.findById(id), HttpStatus.OK);
    }

    @PostMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW)
    public ResponseEntity<HrefEntityDTO> save(@RequestBody @Valid ReviewDTORequest dto) {
        return new ResponseEntity<HrefEntityDTO>(this.reviewService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> update(@RequestBody ReviewDTORequest dto, @PathVariable Long id) {
        return new ResponseEntity<HrefEntityDTO>(this.reviewService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(ThemehubConstant.RESOURCE_REVIEWS + ThemehubConstant.RESOURCE_REVIEWS_REVIEW + ThemehubConstant.RESOURCE_GENERIC_ID)
    public ResponseEntity<HrefEntityDTO> delete(@PathVariable Long id) {
        return new ResponseEntity<HrefEntityDTO>(this.reviewService.delete(id), HttpStatus.OK);
    }
}
