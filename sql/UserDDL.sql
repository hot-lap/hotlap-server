-- User Entity DDL SQL Statements

-- User Table
CREATE TABLE `user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'User ID',
    `name`        VARCHAR(255) NULL COMMENT '사용자 이름',
    `nickname`    VARCHAR(255) NULL COMMENT '사용자 닉네임',
    `email`       VARCHAR(512) NULL COMMENT '암호화된 사용자 이메일',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 엔티티';

-- CredentialUser Table
CREATE TABLE `credential_user`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Credential User ID',
    `uid`          BIGINT       NOT NULL COMMENT 'User ID 참조',
    `username`     VARCHAR(255) NOT NULL COMMENT '사용자 아이디',
    `enc_password` VARCHAR(512) NOT NULL COMMENT '암호화된 비밀번호',
    `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    INDEX `idx__uid` (`uid`),
    FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='자격 증명 사용자 엔티티';
