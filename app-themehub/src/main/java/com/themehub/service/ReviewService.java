package com.themehub.service;


import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.ReviewDTO;
import com.themehub.dto.request.ReviewDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    public HrefEntityDTO save( ReviewDTORequest dto );
    public HrefEntityDTO update( ReviewDTORequest dto , Long id);
    public HrefEntityDTO delete( Long id);
    public ReviewDTO findById( Long id );
    public Page<ReviewDTO> findByRatingJPQL (Long rating, Pageable pageable );
    public Page<ReviewDTO> findByKeyWordJPQL (String key_word, Pageable pageable );

}
