package com.hpprediction.demo.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "appconf")
public class AppPropertiesBound {
    private final Jwt jwt = new Jwt();
    private final OAuth2 oauth2 = new OAuth2();

    public static final class Jwt {
        private String jwtSecret;
        private long jwtExpirationMs;

        public String getJwtSecret() {
            return jwtSecret;
        }

        public void setJwtSecret(String tokenSecret) {
            this.jwtSecret = tokenSecret;
        }

        public long getJwtExpirationMs() {
            return jwtExpirationMs;
        }

        public void setJwtExpirationMs(long tokenExpirationMsec) {

            this.jwtExpirationMs = tokenExpirationMsec;
        }
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    public Jwt getJwt() {
        return jwt;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }
}