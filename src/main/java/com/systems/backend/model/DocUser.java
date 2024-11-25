package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DocUser {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", length = 20)
    private String phone;

    @ColumnDefault("'0'")
    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "email", length = 100)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}