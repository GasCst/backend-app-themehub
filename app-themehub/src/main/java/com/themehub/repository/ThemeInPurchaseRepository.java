package com.themehub.repository;

import com.themehub.model.ThemeInPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeInPurchaseRepository extends JpaRepository<ThemeInPurchase, Long> {
}
