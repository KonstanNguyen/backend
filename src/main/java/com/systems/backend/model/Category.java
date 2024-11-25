package com.systems.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

}