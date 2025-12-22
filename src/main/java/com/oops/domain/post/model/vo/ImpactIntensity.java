package com.oops.domain.post.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ImpactIntensity {

    @Column(name = "impact_intensity", nullable = false)
    private Integer value;

    protected ImpactIntensity() {}

    public ImpactIntensity(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("ImpactIntensity is required");
        }
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.value = value;
    }

    public int value() {
        return value;
    }
}