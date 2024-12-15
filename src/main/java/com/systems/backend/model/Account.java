package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private DocUser user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Account_Role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "historyDownloadId.account", fetch = FetchType.LAZY)
    private Collection<HistoryDownload> historyDownloads;

    @OneToMany(mappedBy = "ratingId.account", fetch = FetchType.LAZY)
    private Collection<Rating> ratings;
}