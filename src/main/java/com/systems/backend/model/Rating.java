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
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", nullable = false)
        private Account account;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "document_id", nullable = false)
        private Document document;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            RatingId entity = (RatingId) o;
            return Objects.equals(this.account, entity.account) &&
                    Objects.equals(this.document, entity.document);
        }

        @Override
        public int hashCode() {
            return Objects.hash(account, document);
        }

    }

    @EmbeddedId
    private RatingId ratingId;

    @Column(name = "rate", nullable = false)
    private Short rate;

}