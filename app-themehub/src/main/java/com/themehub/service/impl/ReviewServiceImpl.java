package com.themehub.service.impl;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.ReviewDTO;
import com.themehub.dto.request.ReviewDTORequest;
import com.themehub.errorhandler.EntityNotFoundException;
import com.themehub.mapper.ReviewMapper;
import com.themehub.model.Review;
import com.themehub.model.Theme;
import com.themehub.model.User;
import com.themehub.repository.ReviewRepository;
import com.themehub.repository.ThemeRepository;
import com.themehub.repository.UserInterface;
import com.themehub.service.ReviewService;
import com.themehub.util.ThemeHubResource;
import com.themehub.util.ThemeHubUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {


    final ReviewRepository reviewRepository;
    final ReviewMapper reviewMapper;
    final ThemeHubUtil util;
    final ThemeRepository themeRepository;
    final UserInterface userInterface;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper,
                             ThemeHubUtil util, ThemeRepository themeRepository,
                             UserInterface userInterface) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.util = util;
        this.themeRepository = themeRepository;
        this.userInterface = userInterface;
    }

    @Override
    public HrefEntityDTO save(ReviewDTORequest dto) {
        Theme theme = this.themeRepository.findById(dto.getIdTheme())
                .orElseThrow( ()-> new EntityNotFoundException("not found idTheme") );
        User user = this.userInterface.findById(dto.getIdUser())
                .orElseThrow( ()-> new EntityNotFoundException("not found idUser") );
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setDate(dto.getDate());
        review.setTheme(theme);
        review.setUser(user);
        return util.createHrefFromResource( this.reviewRepository.save(review).getIdreview(), ThemeHubResource.REVIEW);
    }

    @Override
    public HrefEntityDTO update(ReviewDTORequest dto, Long id) {
        Theme theme = this.themeRepository.findById(dto.getIdTheme())
                .orElseThrow( ()-> new EntityNotFoundException("not found idTheme") );
        User user = this.userInterface.findById(dto.getIdUser())
                .orElseThrow( ()-> new EntityNotFoundException("not found idUser") );
        Review review = this.reviewRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("not found review") );
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setDate(dto.getDate());
        review.setTheme(theme);
        review.setUser(user);
        return util.createHrefFromResource( this.reviewRepository.save(review).getIdreview(), ThemeHubResource.REVIEW);
    }

    @Override
    public HrefEntityDTO delete(Long id) {
        Review review = this.reviewRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("not found review") );
        this.reviewRepository.removeReview(id);
        return util.createHrefFromResource( id , ThemeHubResource.REVIEW);
    }

    @Override
    public ReviewDTO findById(Long id) {
        Review review = this.reviewRepository.searchByID(id)
                .orElseThrow( ()-> new EntityNotFoundException("not found") );
        return this.reviewMapper.toDTO(review);
    }

    @Override
    public Page<ReviewDTO> findByRatingJPQL(Long rating, Pageable pageable) {
        Page<Review> reviews = this.reviewRepository.findByRatingJPQL(rating,pageable);
        return reviews.map( (b)->this.reviewMapper.toDTO(b));
    }

    @Override
    public Page<ReviewDTO> findByKeyWordJPQL(String key_word, Pageable pageable) {
        Page<Review> reviews = this.reviewRepository.findByKeyWordJPQL(key_word,pageable);
        return reviews.map( (b)->this.reviewMapper.toDTO(b));
    }

}
