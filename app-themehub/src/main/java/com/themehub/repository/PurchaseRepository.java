package com.themehub.repository;

import com.themehub.constant.ThemehubConstant;
import com.themehub.model.Purchase;
import com.themehub.model.Review;
import com.themehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.idPurchase=:id")
    Optional<Purchase> searchByID(Long id);


    List<Purchase> findByBuyer(User user);

    List<Purchase> findByPurchaseTime(Date date);

    @Query("select p from Purchase p where p.purchaseTime > ?1 and p.purchaseTime < ?2 and p.buyer = ?3")
    List<Purchase> findByBuyerInPeriod(Date startDate, Date endDate, User user);

}
