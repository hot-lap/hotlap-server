-- User Entity DDL SQL Statements

-- User Table
CREATE TABLE `user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'User ID',
    `name`        VARCHAR(255) NULL COMMENT '사용자 이름',
    `nickname`    VARCHAR(255) NULL COMMENT '사용자 닉네임',
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
    `enc_password` VARCHAR(255) NOT NULL COMMENT '암호화된 비밀번호',
    `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    INDEX `idx__uid` (`uid`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='자격 증명 사용자 엔티티';

-- GoogleUser Table
CREATE TABLE `google_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Google User ID',
    `uid`         BIGINT       NOT NULL COMMENT 'User ID 참조',
    `oauth_id`    VARCHAR(255) NOT NULL COMMENT 'Google OAuth ID',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    INDEX `idx__uid` (`uid`),
    INDEX `oauth_id` (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Google 사용자 엔티티';

-- KakaoUser Table
CREATE TABLE `kakao_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Kakao User ID',
    `uid`         BIGINT       NOT NULL COMMENT 'User ID 참조',
    `oauth_id`    VARCHAR(255) NOT NULL COMMENT 'Kakao OAuth ID',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    INDEX `idx__uid` (`uid`),
    INDEX `oauth_id` (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Kakao 사용자 엔티티';

-- NaverUser Table
CREATE TABLE `naver_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Naver User ID',
    `uid`         BIGINT       NOT NULL COMMENT 'User ID 참조',
    `oauth_id`    VARCHAR(255) NOT NULL COMMENT 'Naver OAuth ID',
    `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    INDEX `idx__uid` (`uid`),
    INDEX `oauth_id` (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Naver 사용자 엔티티';
