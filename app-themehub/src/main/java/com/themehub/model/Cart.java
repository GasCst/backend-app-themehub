package com.themehub.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "cart", schema = "dbo")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_themes", schema = "dbo",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private Set<Theme> themes = new HashSet<>();

    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }


    public Set<Theme> getThemes() {
        return themes;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    public void addTheme(Theme theme) {
        this.themes.add(theme);
    }

    public void removeTheme(Theme theme) {
        this.themes.remove(theme);
    }

    public void clearThemes() {
        this.themes.clear();
    }


}

