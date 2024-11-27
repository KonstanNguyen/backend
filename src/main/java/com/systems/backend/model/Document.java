package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Document {
    @Id
    @Column(name = "document_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Long category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private DocUser author;

    @Nationalized
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Nationalized
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Short status;

    @ColumnDefault("getdate()")
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @ColumnDefault("getdate()")
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

}