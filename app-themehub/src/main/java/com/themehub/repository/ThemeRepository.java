package com.themehub.repository;

import com.themehub.dto.ThemeDTO;
import com.themehub.model.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    public Page<Theme> findByNameLikeAndState (String name, String state, Pageable pePageable);

    Optional<Theme> findById ( Long id );

    Theme findByIdtheme ( Long id );

}
