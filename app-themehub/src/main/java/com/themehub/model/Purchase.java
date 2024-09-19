package com.themehub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_purchase", schema ="dbo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpurchase")
    private Long idPurchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtheme")
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private User buyer;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchasetime")
    private Date purchaseTime;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
    private List<ThemeInPurchase> themesInPurchase;

}

