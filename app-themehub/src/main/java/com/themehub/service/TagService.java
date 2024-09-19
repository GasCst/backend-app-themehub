package com.themehub.service;

import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.TagDTO;
import com.themehub.dto.request.TagDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TagService {

    public HrefEntityDTO save(TagDTORequest dto);

    public HrefEntityDTO update(TagDTORequest dto, Long id);

    public HrefEntityDTO delete(Long id);

    public Page<TagDTO> findByName(String name, Pageable pageable);

    public TagDTO findById(Long id);

}
