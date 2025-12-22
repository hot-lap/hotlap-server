package com.oops.domain.post.model;

import com.oops.domain.post.model.enums.MismatchCategory;
import com.oops.domain.post.model.vo.ImpactIntensity;
import com.oops.outbound.mysql.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "content", nullable = false)
    private String content;

    @Embedded
    private ImpactIntensity impactIntensity;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private MismatchCategory category;

    @Column(name = "cause")
    private String cause;

    @Column(name = "feeling")
    private String feeling;

    public void update(String content, ImpactIntensity impactIntensity,
            MismatchCategory category, String cause, String feeling) {
        this.content = content;
        this.impactIntensity = impactIntensity;
        this.category = category;
        this.cause = cause;
        this.feeling = feeling;
    }
}
