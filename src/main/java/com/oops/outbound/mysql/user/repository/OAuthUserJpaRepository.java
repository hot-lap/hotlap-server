package com.oops.outbound.mysql.user.repository;

import com.oops.domain.user.model.OAuthUser;
import com.oops.domain.user.model.vo.OAuthProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserJpaRepository extends JpaRepository<OAuthUser, Long> {

    Optional<OAuthUser> findByProviderAndOauthId(OAuthProvider provider, String oauthId);

    boolean existsByProviderAndOauthId(OAuthProvider provider, String oauthId);
}
