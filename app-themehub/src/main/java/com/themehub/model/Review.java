package com.themehub.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table( name = "t_review",  schema ="dbo")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idreview;

    @Column( name = "rating", nullable = false)
    private int rating;

    @Column( name = "comment")
    private String comment;

    @Column( name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn( name = "iduser")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn( name = "idtheme")
    @JsonIgnore
    private Theme theme;

}


