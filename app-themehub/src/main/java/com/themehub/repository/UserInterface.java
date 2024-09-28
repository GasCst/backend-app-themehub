package com.themehub.repository;

import com.themehub.constant.ThemehubConstant;
import com.themehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInterface extends JpaRepository<User,Long> {

    @Query(value = ThemehubConstant.SP_SEARCH_USER , nativeQuery = true)
    List<User> findByKeyWordSQL (String key_word, String state);

    @Query(value = ThemehubConstant.SP_SAVE_USER , nativeQuery = true)
    User saveSQL(String username,String email,String password,String first_name,String last_name,String company,String state);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);


}
