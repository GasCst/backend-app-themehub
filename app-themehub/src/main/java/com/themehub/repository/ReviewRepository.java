package com.themehub.repository;

import com.themehub.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.idreview=:id")
    Optional<Review> searchByID(Long id);

    @Modifying
    @Query("delete from Review r where r.idreview = :id")
    void removeReview ( Long id );

    @Query("select r from Review r where r.rating=:rating")
    Page<Review> findByRatingJPQL(Long rating, Pageable pageable );

    @Query("SELECT r FROM Review r " +
            "INNER JOIN r.theme rt " +
            "INNER JOIN r.user ru " +
            "WHERE " +
            "LOWER(CONCAT(r.rating, ' ', r.comment, ' ', r.date, ' ', rt.name, ' ', ru.username, ' ', ru.firstName)) " +
            "LIKE LOWER(CONCAT('%', :key_word, '%'))")
    Page<Review> findByKeyWordJPQL(@Param("key_word") String keyWord, Pageable pageable);


}
