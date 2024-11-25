package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Collection;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Account_Role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Collection<Account> accounts;
}