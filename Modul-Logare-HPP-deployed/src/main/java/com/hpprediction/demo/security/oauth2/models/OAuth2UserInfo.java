package com.hpprediction.demo.security.oauth2.models;

import com.hpprediction.demo.entity.Role;

import java.util.Map;
import java.util.Set;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract Set<Role> getRoles();
}
