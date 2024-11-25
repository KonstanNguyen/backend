package com.systems.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Rating {
    @Getter
    @Setter
    @Embeddable
    public static class RatingId implements java.io.Serializable {
        @Serial
        private static final long serialVersionUID = 5569649995966803905L;
        @Column(name = "account_id", nullable = false)
        private Long accountId;

        @Column(name = "document_id", nullable = false)
        private Long documentId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            RatingId entity = (RatingId) o;
            return Objects.equals(this.accountId, entity.accountId) &&
                    Objects.equals(this.documentId, entity.documentId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountId, documentId);
        }

    }

    @EmbeddedId
    private RatingId id;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "rate", nullable = false)
    private Short rate;

}