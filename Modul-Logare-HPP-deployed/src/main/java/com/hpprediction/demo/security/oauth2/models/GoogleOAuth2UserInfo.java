package com.hpprediction.demo.security.oauth2.models;

import com.hpprediction.demo.entity.Role;

import java.util.Map;
import java.util.Set;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {



    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public Set<Role> getRoles() {
        return (Set<Role>) attributes.get("roles");
    }

}
