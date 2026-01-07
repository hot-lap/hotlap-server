package com.oops.domain.user.model;

import com.oops.domain.user.model.vo.OAuthProvider;
import com.oops.outbound.mysql.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oauth_users", uniqueConstraints = @UniqueConstraint(columnNames = {"provider", "oauth_id"}))
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuthUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oauth_user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 20)
    private OAuthProvider provider;

    @Column(name = "oauth_id", nullable = false)
    private String oauthId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
