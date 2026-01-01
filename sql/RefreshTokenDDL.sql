-- RefreshToken Entity DDL SQL Statements

-- RefreshToken Table
CREATE TABLE `refresh_token`
(
  `uid`           BIGINT       NOT NULL COMMENT 'User ID',
  `refresh_token` VARCHAR(255) NOT NULL COMMENT '리프레시 토큰',
  `created_at`    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `modified_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='리프레시 토큰 엔티티';
