package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Collection<Document> documents;
}