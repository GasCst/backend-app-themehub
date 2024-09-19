package com.themehub.repository;

import com.themehub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    // query tramite nome del metodo
    public List<Category> findByDescriptionContainingOrderByIdAsc( String description );


    // Tramite JPQl
    @Query("select c from Category c where c.id=:id")
    public Category findByIdJPSQL( Long id);


    //Tramite INSTRUZIONE SQL
    @Query(value = "select count(*) > 0 from dbo.t_category where id = :id", nativeQuery = true)
    public boolean existsByIdSQL(Long id);


}
