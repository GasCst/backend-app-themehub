package com.themehub.service.impl;

import com.themehub.constant.ThemehubConstant;
import com.themehub.dto.HrefEntityDTO;
import com.themehub.dto.TagDTO;
import com.themehub.dto.request.TagDTORequest;
import com.themehub.errorhandler.EntityNotFoundException;
import com.themehub.mapper.TagMapper;
import com.themehub.model.Tag;
import com.themehub.repository.TagRepository;
import com.themehub.service.TagService;
import com.themehub.util.ThemeHubResource;
import com.themehub.util.ThemeHubUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TagServiceImpl implements TagService {

    final TagRepository tagRepository;
    final TagMapper tagMapper;
    final ThemeHubUtil util;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper, ThemeHubUtil util) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.util = util;
    }


    @Override
    @Transactional( readOnly = false )
    public HrefEntityDTO save(TagDTORequest dto) {
        Tag tag= new Tag();
        tag.setName(dto.getName());
        tag.setState(dto.getState());
        System.out.println(tag.getId()+" "+tag.getName()+" "+tag.getState());
        this.tagRepository.save(tag);
        return this.util.createHrefFromResource( tag.getId(), ThemeHubResource.TAG);
    }

    @Override
    @Transactional( readOnly = false )
    public HrefEntityDTO update(TagDTORequest dto, Long id) {
        Tag tag= this.tagRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("not found"));
        tag.setName(dto.getName());
        tag.setState(dto.getState());
        return this.util.createHrefFromResource( this.tagRepository.save(tag).getId(), ThemeHubResource.TAG);
    }

    @Override
    @Transactional( readOnly = false )
    public HrefEntityDTO delete(Long id) {
        Tag tag= this.tagRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("not found"));
        tag.setState(ThemehubConstant.STATE_INACTIVE);
        return this.util.createHrefFromResource( this.tagRepository.save(tag).getId(), ThemeHubResource.TAG);
    }

    @Transactional( readOnly = true )
    @Override
    public Page<TagDTO> findByName(String name, Pageable pageable) {
        Page<Tag> tags = this.tagRepository.findByNameContainingAndState( name, ThemehubConstant.STATE_ACTIVE, pageable);
        return tags.map( (bean)-> this.tagMapper.toDTO(bean));
    }

    @Transactional( readOnly = true )
    @Override
    public TagDTO findById(Long id) {
        Tag tag = this.tagRepository.findByIdAndState(id, ThemehubConstant.STATE_ACTIVE )
                .orElseThrow( ()-> new EntityNotFoundException("not found") );
        return this.tagMapper.toDTO(tag);
    }

}
