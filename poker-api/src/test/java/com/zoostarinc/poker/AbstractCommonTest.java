package com.zoostarinc.poker;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractCommonTest {

	@Autowired
	protected MockMvc endpoint;

	protected ObjectMapper om = objectMapper();

	protected ObjectMapper objectMapper() {
		var value = new ObjectMapper();
		value.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return value;
	}

	protected OidcUser oidcUser() {
		Map<String, Object> claims = new HashMap<>();
		claims.put(StandardClaimNames.SUB, "testuser");
		claims.put(StandardClaimNames.GIVEN_NAME, "junit");
		claims.put(StandardClaimNames.EMAIL, "test@example.com");
		OidcIdToken idToken = new OidcIdToken("tokenValue", Instant.now(), Instant.now().plusSeconds(3600), claims);
		OidcUserInfo userInfo = new OidcUserInfo(claims);
		return new DefaultOidcUser(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), idToken,
				userInfo);
	}

}
