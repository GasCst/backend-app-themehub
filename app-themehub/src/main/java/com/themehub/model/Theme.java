package com.themehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.List;


@Data
@ToString
@Entity
@Table(name = "t_theme", schema ="dbo")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtheme;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String previewImage;

    @Column(length = 255)
    private String demoUrl;

    @Column(length = 50)
    private String category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 100)
    private String author;

    @Column(length = 255)
    private String authorProfile;

    @Column(length = 255)
    private String downloadLink;

    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) DEFAULT 'active'")
    private String state;

    @OneToMany ( targetEntity = Review.class, mappedBy = "theme")
    public List<Review> reviews;

    @OneToMany ( targetEntity = ThemeInPurchase.class, mappedBy = "theme", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    public List<ThemeInPurchase> themeInPurchases;


//    // Relazione ManyToMany con Tag
//    @ManyToMany
//    @JoinTable(
//            name = "theme_tag",  // Nome della tabella di join
//            schema = "dbo",
//            joinColumns = @JoinColumn(name = "theme_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private Set<Tag> tags = new HashSet<>();



}

