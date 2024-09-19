package com.themehub.model;

import com.themehub.constant.ThemehubConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= ThemehubConstant.TAB_NAME_TAG, schema = ThemehubConstant.SEQ_NAME_USER)
public class Tag {

    @Id
    @Column(name="idtag")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="state")
    private String state;


//    @ManyToMany(mappedBy = "tags")
//    private Set<Theme> themes = new HashSet<>();

}
