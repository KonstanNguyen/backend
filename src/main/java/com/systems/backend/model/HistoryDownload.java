package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "History_Download")
public class HistoryDownload {
    @Getter
    @Setter
    @Embeddable
    public static class HistoryDownloadId implements java.io.Serializable {
        @Serial
        private static final long serialVersionUID = 7789561099938143923L;
        @Column(name = "account_id", nullable = false)
        private Long accountId;

        @Column(name = "document_id", nullable = false)
        private Long documentId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            HistoryDownloadId entity = (HistoryDownloadId) o;
            return Objects.equals(this.accountId, entity.accountId) &&
                    Objects.equals(this.documentId, entity.documentId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountId, documentId);
        }

    }

    @EmbeddedId
    private HistoryDownloadId id;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}