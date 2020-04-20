package com.forum.forum.model;

import com.forum.forum.other.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "usermodel")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Token token;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Report> report = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();

    @OneToMany(mappedBy = "starUser", orphanRemoval = true)
    private Set<Star> stars = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Star> starUsers = new HashSet<>();

    private String motto;

    public User() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
