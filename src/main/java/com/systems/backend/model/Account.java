package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @Column(name = "account_id", nullable = false)
    private Long id;

    @Immutable
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private DocUser user;

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Collection<Role> roles;
}