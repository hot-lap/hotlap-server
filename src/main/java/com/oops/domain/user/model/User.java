package com.oops.domain.user.model;

import com.oops.common.encrypt.EncryptConverter;
import com.oops.common.encrypt.EncryptData;
import com.oops.outbound.mysql.common.BaseEntity;
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
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Convert(converter = EncryptConverter.class)
    private EncryptData email;

    /** 사용자 성명 */
    private String name;

    /** 서비스에서 사용할 닉네임 */
    private String nickname;
}
