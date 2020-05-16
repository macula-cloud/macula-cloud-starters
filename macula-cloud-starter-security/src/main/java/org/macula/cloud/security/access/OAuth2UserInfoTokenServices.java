package org.macula.cloud.security.access;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class OAuth2UserInfoTokenServices extends UserInfoTokenServices {

	public OAuth2UserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
		super(userInfoEndpointUrl, clientId);
	}

	@Override
	@Cacheable(cacheNames = OAuth2AccessToken.ACCESS_TOKEN, key = "#{accessToken}")
	public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
		return super.loadAuthentication(accessToken);
	}

}
