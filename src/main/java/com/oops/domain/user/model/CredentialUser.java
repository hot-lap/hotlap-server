package com.oops.domain.user.model;

import com.oops.outbound.mysql.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credential_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private String username;

    @Column(name = "enc_password", nullable = false)
    private String passwordHash;
}
