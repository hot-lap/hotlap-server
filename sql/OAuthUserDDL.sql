-- OAuthUser Table
CREATE TABLE `oauth_users`
(
    `oauth_user_id` BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'OAuth User ID',
    `provider`      VARCHAR(20)  NOT NULL COMMENT 'OAuth 제공자 (GOOGLE, KAKAO, NAVER)',
    `oauth_id`      VARCHAR(255) NOT NULL COMMENT 'OAuth ID',
    `user_id`       BIGINT       NOT NULL COMMENT 'User ID 참조',
    `created_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`oauth_user_id`),
    UNIQUE KEY `uk_provider_oauth_id` (`provider`, `oauth_id`),
    INDEX `idx_user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='OAuth 사용자 엔티티';
