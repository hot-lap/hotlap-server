package com.oops.domain.user.model;

import com.oops.common.encrypt.EncryptConverter;
import com.oops.common.encrypt.EncryptData;
import com.oops.outbound.mysql.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
	private Long id = 0L;

	private Long uid;

	private String username;

	@Column(name = "enc_password")
	@Convert(converter = EncryptConverter.class)
	private EncryptData encPassword;

}
